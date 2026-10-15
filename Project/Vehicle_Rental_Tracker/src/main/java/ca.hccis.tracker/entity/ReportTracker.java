package ca.hccis.tracker.entity;

import ca.hccis.tracker.entity.VehicleRentalTrackerData;

import java.util.ArrayList;

public class ReportTracker {
    private String fName;
    private String lName;
    private String address;
    private String vehicleColour;
    private String vehicleType;
    private int rentalTimeDays;
    private double saleTotal;

    private ArrayList<VehicleRentalTrackerData> vehicleRentalTrackerData;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getRentalTimeDays() {
        return rentalTimeDays;
    }

    public void setRentalTimeDays(int rentalTimeDays) {
        this.rentalTimeDays = rentalTimeDays;
    }

    public double getSaleTotal() {
        return saleTotal;
    }

    public ArrayList<VehicleRentalTrackerData> getVehicleRentalTrackerData() {
        return vehicleRentalTrackerData;
    }

    public void setVehicleRentalTrackerData(ArrayList<VehicleRentalTrackerData> vehicleRentalTrackerData) {
        this.vehicleRentalTrackerData = vehicleRentalTrackerData;
    }
}
