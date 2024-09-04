package com.boozni.observer;

public class PhoneDisplay extends Display {
    @Override
    public void display() {
        System.out.println("Phone Display - Temperature: " + temperature + " Humidity: " + humidity + " Pressure: " + pressure);
    }
}
