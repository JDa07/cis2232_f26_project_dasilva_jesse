package ca.hccis.tracker.threads;

import ca.hccis.tracker.entity.VehicleRentalOrder;
import ca.hccis.tracker.util.CisUtility;
import ca.hccis.tracker.util.RentalStorage;

import java.io.IOException;
import java.util.List;

/**
 * Thread that runs the rental menu. The same class is used for both threads:
 * give it a console CisUtility for the console thread, or a GUI CisUtility
 * for the JOptionPane thread. Both threads save to the same file through
 * RentalStorage, so they share the accumulated data.
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

    private CisUtility cisUtility;

    public ThreadTracker() {
        this(new CisUtility());
    }

    public ThreadTracker(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    public void setCisUtility(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    @Override
    public void run() {

        String menuOption;

        do {
            String input = cisUtility.getInputString(MENU);

            // null means the JOptionPane dialog was closed or cancelled
            menuOption = (input == null) ? EXIT : input.trim().toLowerCase();

            switch (menuOption) {
                case EXIT:
                    cisUtility.display(MESSAGE_EXIT);
                    break;

                case "1":
                    processAdd();
                    break;

                case "2":
                    processShow();
                    break;

                default:
                    cisUtility.display(MESSAGE_ERROR);
                    break;
            }

        } while (!menuOption.equals(EXIT));
    }

    /**
     * Get a rental from the user, save it, and show the updated accumulator
     */
    public void processAdd() {
        VehicleRentalOrder rentalOrder = new VehicleRentalOrder();

        if (!rentalOrder.getInformation(cisUtility)) {
            return; // user cancelled (a message was already shown)
        }

        if (!rentalOrder.isComplete()) {
            cisUtility.display("Order incomplete");
            return;
        }

        rentalOrder.setSaleID(VehicleRentalOrder.generateID());

        try {
            int addedThisRun = RentalStorage.save(rentalOrder);
            int totalInFile = RentalStorage.countOrdersInFile();

            cisUtility.display(MESSAGE_SUCCESS + System.lineSeparator()
                    + rentalOrder
                    + "Orders added this run (both threads): " + addedThisRun + System.lineSeparator()
                    + "Total orders in file: " + totalInFile);

        } catch (IOException e) {
            cisUtility.display("ERROR saving order: " + e.getMessage());
        }
    }

    /**
     * Show every order saved by both threads
     */
    public void processShow() {
        try {
            List<VehicleRentalOrder> orders = RentalStorage.readAll();

            if (orders.isEmpty()) {
                cisUtility.display("No orders found");
                return;
            }

            StringBuilder output = new StringBuilder("Orders found: " + orders.size()
                    + System.lineSeparator() + System.lineSeparator());

            for (VehicleRentalOrder current : orders) {
                output.append(current).append(System.lineSeparator());
            }

            cisUtility.display(output.toString());

        } catch (IOException e) {
            cisUtility.display("ERROR reading file: " + e.getMessage());
        }
    }
}