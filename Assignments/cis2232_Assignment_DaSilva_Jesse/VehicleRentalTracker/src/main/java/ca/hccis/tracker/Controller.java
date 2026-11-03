package ca.hccis.tracker;

import ca.hccis.tracker.threads.ThreadTracker;
import ca.hccis.tracker.util.CisUtility;
import ca.hccis.tracker.util.RentalStorage;

/**
 * Controls the program: starts one thread that works in the console and one
 * that works with JOptionPane. Both run the same menu and share the same file.
 *
 * @author Jesse Da Silva
 * @since 22-09-2026
 */

public class Controller {

    public static void main(String[] args) {

        // continue sale IDs after the ones already in the file
        RentalStorage.syncIdCounter();

        ThreadTracker consoleThread = new ThreadTracker(new CisUtility(false));
        consoleThread.setName("Console thread");

        ThreadTracker guiThread = new ThreadTracker(new CisUtility(true));
        guiThread.setName("JOptionPane thread");

        consoleThread.start();
        guiThread.start();

        // wait for both threads to finish before the final summary
        try {
            consoleThread.join();
            guiThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Orders added this run (both threads): " + RentalStorage.getOrdersAdded());
        System.out.println("Total orders in file: " + RentalStorage.countOrdersInFile());
    }
}