package iTravelSystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Flights {
    public int getFlightNumber() {
        return flightNumber.get();
    }

    public String getOrigin() {
        return origin.get();
    }

    public String getDestination() {
        return destination.get();
    }

    public String getDepartTime() {
        return departTime.get();
    }

    public String getFlightTime() {
        return flightTime.get();
    }

    public String getAircraft() {
        return aircraft.get();
    }

    public int getAircraftNumber() {
        return aircraftNumber.get();
    }

    public int getSeatCapacity() {
        return seatCapacity.get();
    }

    private final IntegerProperty flightNumber;
    private final StringProperty origin;
    private final StringProperty destination;
    private final StringProperty departTime;
    private final StringProperty flightTime;
    private final StringProperty aircraft;
    private final IntegerProperty aircraftNumber;
    private final IntegerProperty seatCapacity;

    //public Flights(int flightNumber) { this.flightNumber = new SimpleIntegerProperty(flightNumber);}

    public Flights(int flightNumber, String origin, String destination, String departTime, String flightTime,
                   String aircraft, int aircraftNumber, int seatCapacity)
    {
        this.flightNumber = new SimpleIntegerProperty(flightNumber);
        this.origin = new SimpleStringProperty(origin);
        this.destination = new SimpleStringProperty(destination);
        this.departTime = new SimpleStringProperty(departTime);
        this.flightTime = new SimpleStringProperty(flightTime);
        this.aircraft = new SimpleStringProperty(aircraft);
        this.aircraftNumber = new SimpleIntegerProperty(aircraftNumber);
        this.seatCapacity = new SimpleIntegerProperty(seatCapacity);


    }

    public IntegerProperty flightNumberProperty() { return flightNumber;}
    public StringProperty originProperty() { return origin;}
    public StringProperty destinationProperty() {return destination;}
    public StringProperty departTimeProperty() { return departTime;}
    public StringProperty flightTimeProperty() {return flightTime;}
    public StringProperty aircraftProperty() {return aircraft;}
    public IntegerProperty aircraftNumberProperty() {return aircraftNumber;}
    public IntegerProperty seatCapacityProperty() {return seatCapacity;}

    public void setFlightNumber(int flightNumber) {
        this.flightNumber.set(flightNumber);
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public void setDepartTime(String departTime) {
        this.departTime.set(departTime);
    }

    public void setFlightTime(String flightTime) {
        this.flightTime.set(flightTime);
    }

    public void setAircraft(String aircraft) {
        this.aircraft.set(aircraft);
    }

    public void setAircraftNumber(int aircraftNumber) {
        this.aircraftNumber.set(aircraftNumber);
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity.set(seatCapacity);
    }
}
