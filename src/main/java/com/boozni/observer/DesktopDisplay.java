package com.boozni.observer;

public class DesktopDisplay extends Display {
    @Override
    public void display() {
        System.out.println("Desktop Display - Temperature: " + temperature + " Humidity: " + humidity + " Pressure: " + pressure);
    }
}
