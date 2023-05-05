/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;


import Event.entities.Event;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import Services.Event.EventCRUD;

import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class CalendarController implements Initializable {
ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
    try {
        drawCalendar();
    } catch (SQLException ex) {
        Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    void backOneMonth(ActionEvent event) throws SQLException {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) throws SQLException {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() throws SQLException{
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();
           EventCRUD as = new EventCRUD();
        //List of activities for a given month
        Map<Integer, List<Event>> calendarActivityMap = createCalendarMap(as.getCalendarActivitiesMonth(dateFocus));
        
        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                      List<Event> calendarActivities = calendarActivityMap.get(currentDate);
                      
                        if(calendarActivities != null){
                            
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<Event> Activites, double rectangleHeight, double rectangleWidth, StackPane stackPane){
    VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER); // set the alignment to CENTER

    vBox.setSpacing(rectangleHeight/10);
    

    for(Event activite : Activites){
        Text eventText = new Text(activite.getNom());
        eventText.setWrappingWidth(rectangleWidth);
        vBox.getChildren().add(eventText);
    }
    
    Text dateText = new Text(String.valueOf(Activites.get(0).getDate_debut().getDate()));
    dateText.setFill(Color.BLACK);
    dateText.setStyle("-fx-font-weight: bold;");
    vBox.getChildren().add(0, dateText);


 
    vBox.setStyle("-fx-background-color:"+generateRandomColor()+"; -fx-border-color: black; -fx-border-width: 1px;");

    stackPane.getChildren().add(vBox);

   
}

    private Map<Integer, List<Event>> createCalendarMap(List<Event> calendarActivities) {
        Map<Integer, List<Event>> calendarActivityMap = new HashMap<>();

        for (Event activity: calendarActivities) {
            int activityDate = activity.getDate_debut().getDate();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, Arrays.asList(activity));
            } else {
                List<Event> OldListByDate = calendarActivityMap.get(activityDate);

                List<Event> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

   
    
    
     public  String generateRandomColor() {
        Random random = new Random();
    int r = random.nextInt(256);
    int g = random.nextInt(256);
    int b = random.nextInt(256);
    return String.format("#%02x%02x%02x", r, g, b);
    }

    @FXML
    private void backeve(ActionEvent event) throws IOException {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontEvent.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }
}