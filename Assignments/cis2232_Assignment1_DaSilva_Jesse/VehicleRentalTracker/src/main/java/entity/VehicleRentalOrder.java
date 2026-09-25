package entity;
import com.google.gson.Gson;
import util.CisUtility;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.NumberFormat;

/**
 *  Vehicle rental order application.
 *
 * @author Jesse Da Silva
 * @since 22-09-2026
 */

public class VehicleRentalOrder {

    private int saleID;                     //ID used to differentiate sales
    private String vehicleType;             //Type of vehicle (Car, Van, Truck, etc.)
    private String vehicleColour;
    private int rentalTimeDays;             //Number of days vehicle is rented for
    private String custFName;
    private String custLName;
    private String custAddress;
    private String saleTotal;               //Total cost of rental
    private String rentDate;                //Date of rental sale

    private int vehicleCost;                // Added new variable to store vehicle cost

    public static final String VEHICLE_MENU =
            "a) Car" + System.lineSeparator()
            + "b) Van" + System.lineSeparator()
            + "c) Truck" + System.lineSeparator()
            + "d) Electric" + System.lineSeparator()
            + "x) EXIT" + System.lineSeparator();

    public static final String EXIT = "x";

    public static final int CAR_PRICE = 35;
    public static final int VAN_PRICE = 49;
    public static final int TRUCK_PRICE = 40;
    public static final int ELECTRIC_PRICE = 45;
    public static final double TAX_RATE = 1.15;

    public static final AtomicInteger COUNTER = new AtomicInteger(1000);

/*
* Need to add validation to user input
 */
    /**
     *  Gets rental information from the user.
     *
     * @author Jesse Da Silva
     * @since 24-09-2026
     */
    public boolean getInformation(){

        custFName = CisUtility.getInputString("First name: ");
        custLName = CisUtility.getInputString("Last Name: ");
        rentDate = CisUtility.getInputString("Date (yyyy-mm-dd): ");
        custAddress = CisUtility.getInputString("Address: ");
        /*vehicleType = CisUtility.getInputString("Enter vehicle type: ");*/
        boolean validChoice = false;
        do {
            String choice = CisUtility.getInputString("Choose Vehicle Type: \n"
                    + VEHICLE_MENU.trim().toLowerCase());

            switch (choice){
                case "a":
                    vehicleType = "Car";
                    vehicleCost = CAR_PRICE;
                    validChoice = true;
                    break;

                case "b":
                    vehicleType = "Van";
                    vehicleCost = VAN_PRICE;
                    validChoice = true;
                    break;

                case "c":
                    vehicleType = "Truck";
                    vehicleCost = TRUCK_PRICE;
                    validChoice = true;
                    break;

                case "d":
                    vehicleType = "Electric";
                    vehicleCost = ELECTRIC_PRICE;
                    validChoice = true;
                    break;

                case EXIT:
                    System.out.println("Order Cancelled, goodbye");
                    return false;

                default: System.out.println("ERROR please enter valid input");
            }

        } while (!validChoice);
        vehicleColour = CisUtility.getInputString("Enter vehicle colour: ");
        rentalTimeDays = CisUtility.getInputInt("Enter amount of days vehicle will be rented for: ");
        calculateTotal();
        return true;
    }

    /**
     *  Method to validate if user input has blank fields. Will not save to JSON file if so.
     *
     * @author Jesse Da Silva
     * @since 24-09-2026
     */

    public boolean isComplete() {
        return notBlank(custFName) && notBlank(custLName) && notBlank(custAddress)
                && notBlank(rentDate) && notBlank(vehicleType) && notBlank(vehicleColour)
                && vehicleCost > 0 && rentalTimeDays > 0;
    }

    private static boolean notBlank(String s){
        return s != null && !s.isBlank();
    }

    /**
     * Method to calculate sale total
     *
     * @author Jesse Da Silva
     * @since 24-09-2026
     */

    public String calculateTotal(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
        BigDecimal total = BigDecimal.valueOf(vehicleCost)
                .multiply(BigDecimal.valueOf(rentalTimeDays))
                .multiply(BigDecimal.valueOf(TAX_RATE));

        saleTotal =formatter.format(total);
        return saleTotal;
    }

    //Constructor for saleID//
    public VehicleRentalOrder(){
        this.saleID = generateID();
    }

    //Method to generate an order ID//
    public static int generateID(){
        return COUNTER.incrementAndGet();
    }

    /**
     * Method to write data into Json file
     *
     * @author Jesse Da Silva
     * @since 24-09-2026
     */
    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    //Format and display information to user
    public String toString(){
        return "> ORDER SUMMARY <" +
                "\nSale ID: " + saleID +
                "\nDate of Order Placed: " + rentDate +
                "\n---------------------"+
                "\nName: " + custFName +" "+ custLName +
                "\nAddress: " + custAddress +
                "\nVehicle Type: " + vehicleType +
                "\nVehicle Colour: " + vehicleColour +
                "\nRental Length: " + rentalTimeDays + " days" +
                "\nTotal Cost: " + saleTotal + System.lineSeparator();
    }

    //GETTERS AND SETTERS//
    public int getSaleID() {return saleID;}
    public void setSaleID(int saleID) {this.saleID = saleID;}

    public String getVehicleType() {return vehicleType;}
    public void setVehicleType(String vehicleType) {this.vehicleType = vehicleType;}

    public String getVehicleColour() {return vehicleColour;}
    public void setVehicleColour(String vehicleColour) {this.vehicleColour = vehicleColour;}

    public int getRentalTimeDays() {return rentalTimeDays;}
    public void setRentalTimeDays(int rentalTimeDays) {this.rentalTimeDays = rentalTimeDays;}

    public String getCustFName() {return custFName;}
    public void setCustFName(String custFName) {this.custFName = custFName;}

    public String getCustLName() {return custLName;}
    public void setCustLName(String custLName) {this.custLName = custLName;}

    public String getCustAddress() {return custAddress;}
    public void setCustAddress(String custAddress) {this.custAddress = custAddress;}

    public String getSaleTotal() {return saleTotal;}
    public void setSaleTotal(String saleTotal) {this.saleTotal = saleTotal;}

    public String getRentDate() {return rentDate;}
    public void setRentDate(String rentDate) {this.rentDate = rentDate;}

    public int getVehicleCost() {return vehicleCost;}
    public void setVehicleCost(int vehicleCost) {this.vehicleCost = vehicleCost;}
}
