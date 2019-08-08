package com.sam.samcombsfinal;

public class Event {
    private String eventName;
    private String eventAddress;
    private String eventDate;
    private String eventLocation;
    private String eventPhone;

    public Event(String eventName,String eventAddress,String eventDate,String eventLocation,String eventPhone) {
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventPhone = eventPhone;
    }

    public Event() {
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventPhone() {
        return eventPhone;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventPhone(String eventPhone) {
        this.eventPhone = eventPhone;
    }
}
