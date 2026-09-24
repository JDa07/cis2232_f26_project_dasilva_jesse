package ca.hccis.tracker;

import com.google.gson.Gson;
import util.CisUtility;
import entity.VehicleRentalOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controls the program
 *
 * @author Jesse Da Silva
 * @since 22-09-2026
 */

public class Controller {

    public static final String EXIT = "x";

    public static final String
            MENU = "1) Add Rental" + System.lineSeparator()
            + "2) View Rentals" + System.lineSeparator()
            + "X) Exit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";
    public static final String PATH = "C:\\Users\\jdasilva146313\\OneDrive - Holland College\\Documents\\GitHub\\cis2232_f26_project_dasilva_jesse\\Assignments\\cis2232_Assignment1_DaSilva_Jesse\\";
    public static final String FILE_NAME = "orders_dasilva_jesse.json";
    private static Path journalPath = null;
    private static FileWriter journalWriter = null;


    public static void main(String[] args) {

        journalPath = Paths.get(PATH + FILE_NAME);

        if (!Files.exists(journalPath)) {
            File journalFile = new File(journalPath.toString());
        }
        try {
            journalWriter = new FileWriter(PATH + FILE_NAME, true);
        } catch (IOException e) {
            System.out.println("Error creating file writer");
            throw new RuntimeException(e);
        }

        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU).trim().toLowerCase();

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;

                case "1":
                    addRental();
                    break;

                case "2":
                    viewRentals();
                    break;

                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }

        } while (menuOption != EXIT);

        try {
            journalWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addRental() {

        VehicleRentalOrder rentalOrder = new VehicleRentalOrder();
        if (!rentalOrder.getInformation() || !rentalOrder.isComplete()) {
            System.out.println("Order incomplete");
            return;
        }
        rentalOrder.setSaleID(VehicleRentalOrder.generateID());

        try {
            journalWriter.write(rentalOrder.toJson() + System.lineSeparator());
            journalWriter.flush();
            System.out.println(MESSAGE_SUCCESS);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void viewRentals() {
        Gson gson = new Gson();

        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH + FILE_NAME));

            if (lines.isEmpty()) {
                System.out.println("No orders found");

            } else {
                System.out.println("Orders found: " + System.lineSeparator());

                for (String current : lines) {
                    VehicleRentalOrder rentalOrder = gson.fromJson(current, VehicleRentalOrder.class);
                    System.out.println(rentalOrder.toString());
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR reading file");
            throw new RuntimeException(e);
        }

    }
}
