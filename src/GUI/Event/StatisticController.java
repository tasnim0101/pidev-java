/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Event.entities.Don;
import Event.entities.EventDonation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import Services.Event.DonCRUD;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class StatisticController implements Initializable {
    
    
    
     @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BarChart<String, Number> donationsChart;
    
    @FXML
    private CategoryAxis xAxis;
    @FXML
private BarChart<String, Number> barChart;

    
    @FXML
    private NumberAxis yAxis;
    
    private ObservableList<Don> donList;
    
        private ObservableList<String> eventNames = FXCollections.observableArrayList();
    
    private Map<String, Float> eventDonationsMap = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
              // Set the axis labels
        xAxis.setLabel("Event Name");
        yAxis.setLabel("Total Donations");
        
        // Load the data into the chart
        loadChartData();
        
        
        // TODO
    }    
    
    
    
  private void loadChartData() {
    // Get all the donations from the database
    DonCRUD donCRUD = new DonCRUD();
    List<Don> allDonList = donCRUD.consulterDon();
    
    // Create a map to store the total donations for each event
    Map<String, Float> eventDonationsMap = new HashMap<>();
    for (Don don : allDonList) {
        String eventName = don.getEvents().getNom();
        if (eventDonationsMap.containsKey(eventName)) {
            Float totalDonations = eventDonationsMap.get(eventName);
            totalDonations += don.getMontant();
            eventDonationsMap.put(eventName, totalDonations);
        } else {
            eventDonationsMap.put(eventName, don.getMontant());
        }
    }
    
    // Convert the map to a list of EventDonation objects and sort by total donations
    List<EventDonation> eventDonationList = new ArrayList<>();
    for (Map.Entry<String, Float> entry : eventDonationsMap.entrySet()) {
        String eventName = entry.getKey();
        Float totalDonations = entry.getValue();
        EventDonation eventDonation = new EventDonation(eventName, totalDonations);
        eventDonationList.add(eventDonation);
    }
    Collections.sort(eventDonationList);
    
    // Add the event names to the chart and create a series for the total donations
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    for (EventDonation eventDonation : eventDonationList) {
        String eventName = eventDonation.getEventName();
        Float totalDonations = eventDonation.getTotalDonations();
        series.getData().add(new XYChart.Data<>(eventName, totalDonations));
        eventNames.add(eventName);
    }
    
    // Add the series to the chart
    barChart.getData().add(series);
}

    
}
