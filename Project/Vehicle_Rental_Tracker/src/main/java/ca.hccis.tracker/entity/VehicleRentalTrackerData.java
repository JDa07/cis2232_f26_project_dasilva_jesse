package ca.hccis.tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "VehicleRentalTrackerData")

public class VehicleRentalTrackerData {

    public VehicleRentalTrackerData() {
        this.rentalTimeDays = 0;
        this.saleTotal = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleID", nullable = false)
    private Integer saleID;

    @Size(min = 1, max = 10)
    @NotNull
    @Column(name = "rentDate", nullable = false, length = 10)
    private String rentDate;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "custFName", nullable = false, length = 50)
    private String custFName;

    @Size(min = 1, max = 10)
    @NotNull
    @Column(name = "custLName", nullable = false, length = 50)
    private String custLName;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "custAddress", nullable = false, length = 50)
    private String custAddress;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "vehicleColour", nullable = false, length = 20)
    private String vehicleColour;

    @Size(min = 1, max = 50)
    @NotNull
    @Column(name = "vehicleType", nullable = false, length = 50)
    private String vehicleType;

    @Column(name = "rentalTimeDays")
    private int rentalTimeDays;

    @Column(name = "saleTotal")
    private double saleTotal;

                    //GETTERS AND SETTERS//

    public Integer getSaleID() {return saleID;}
    public void setSaleID(Integer saleID) {this.saleID = saleID;}

    public String getRentDate() {return rentDate;}
    public void setRentDate(String rentDate) {this.rentDate = rentDate;}

    public String getCustFName() {return custFName;}
    public void setCustFName(String custFName) {this.custFName = custFName;}

    public String getCustLName() {return custLName;}
    public void setCustLName(String custLName) {this.custLName = custLName;}

    public String getCustAddress() {return custAddress;}
    public void setCustAddress(String custAddress) {this.custAddress = custAddress;}

    public String getVehicleColour() {return vehicleColour;}
    public void setVehicleColour(String vehicleColour) {this.vehicleColour = vehicleColour;}

    public String getVehicleType() {return vehicleType;}
    public void setVehicleType(String vehicleType) {this.vehicleType = vehicleType;}

    public int getRentalTimeDays() {return rentalTimeDays;}
    public void setRentalTimeDays(int rentalTimeDays) {this.rentalTimeDays = rentalTimeDays;}

    public double getSaleTotal() {return saleTotal;}
    public void setSaleTotal(double saleTotal) {this.saleTotal = saleTotal;}

    @Override
    public String toString(){
        return "VehicleRentalTracker\n" +
                "rentDate = " + rentDate +"\n" +
                "custFName = " + custFName + "\n" +
                "custLName = " + custLName + "\n" +
                "custAddress = " + custAddress + "\n" +
                "vehicleColour = " + vehicleColour + "\n" +
                "vehicleType = " + vehicleType + "\n" +
                "rentalTimeDays = " + rentalTimeDays + "\n" +
                "saleTotal = " + saleTotal + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VehicleRentalTrackerData)) return false;
        VehicleRentalTrackerData that = (VehicleRentalTrackerData) o;
        return Objects.equals(getSaleID(), that.getSaleID());
    }

    @Override
    public int hashCode() {return Objects.hashCode(getSaleID());}
}
