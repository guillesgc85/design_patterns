package com.boozni.observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherStationTest {

    private WeatherStation weatherStation;
    private PhoneDisplay phoneDisplay;
    private DesktopDisplay desktopDisplay;

    @BeforeEach
    public void init() {
        weatherStation = new WeatherStation();
        phoneDisplay = new PhoneDisplay();
        desktopDisplay = new DesktopDisplay();
    }

    @Test
    public void shouldNotifyObserversWithUpdatedMeasurements() {

        // Subscribe observers to the weather station
        weatherStation.subscribe(phoneDisplay);
        weatherStation.subscribe(desktopDisplay);

        // Update measurements
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.1f);

        // Assert that observers received the correct updates
        Assertions.assertEquals(25.5f, phoneDisplay.getTemperature());
        Assertions.assertEquals(65.0f, phoneDisplay.getHumidity());
        Assertions.assertEquals(1013.1f, phoneDisplay.getPressure());

        Assertions.assertEquals(25.5f, desktopDisplay.getTemperature());
        Assertions.assertEquals(65.0f, desktopDisplay.getHumidity());
        Assertions.assertEquals(1013.1f, desktopDisplay.getPressure());

        // Unsubscribe the desktopDisplay observer from the weather station
        weatherStation.unsubscribe(desktopDisplay);
        // Update measurements again
        weatherStation.setMeasurements(26.7f, 70.3f, 1012.5f);

        // Assert that observers received the new updates
        Assertions.assertEquals(26.7f, phoneDisplay.getTemperature());
        Assertions.assertEquals(70.3f, phoneDisplay.getHumidity());
        Assertions.assertEquals(1012.5f, phoneDisplay.getPressure());
    }
}