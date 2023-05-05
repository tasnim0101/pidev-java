/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

/**
 *
 * @author tasnim
 */
public class EventDonation implements Comparable<EventDonation> {
    private String eventName;
    private Float totalDonations;

    public EventDonation(String eventName, Float totalDonations) {
        this.eventName = eventName;
        this.totalDonations = totalDonations;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Float getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(Float totalDonations) {
        this.totalDonations = totalDonations;
    }

    @Override
    public int compareTo(EventDonation other) {
        return other.totalDonations.compareTo(this.totalDonations);
    }
}

