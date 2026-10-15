package ca.hccis.tracker.threads;

import ca.hccis.tracker.entity.VehicleRentalOrder;
import ca.hccis.tracker.util.CisUtility;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Thread to run in console
 *
 * @author Jesse Da Silva
 * @since 15/10/2026
 */
public class ThreadTracker extends Thread {

    public static final String EXIT = "x";

    public static final String
            MENU = "1) Add Rental" + System.lineSeparator()
            + "2) View Rentals" + System.lineSeparator()
            + "X) Exit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";
    public static final String PATH = "c:\\CIS2232\\";
    public static final String FILE_NAME = "data_dasilva_jesse.json";
    private static Path journalPath = null;
    private static FileWriter journalWriter = null;

    private CisUtility cisUtility = null;

    public ThreadTracker() {}

    public ThreadTracker(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    public void setCisUtility(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    public void run() {
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
                    processAdd();
                    break;

                case "2":
                    processShow();
                    break;

                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }

        } while (!menuOption.equals(EXIT));

        try {
            journalWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processAdd() {
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

    public void processShow() {
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

