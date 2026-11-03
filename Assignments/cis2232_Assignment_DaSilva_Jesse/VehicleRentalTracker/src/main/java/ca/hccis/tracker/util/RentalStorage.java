package ca.hccis.tracker.util;

import ca.hccis.tracker.entity.VehicleRentalOrder;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Shared storage for rental orders. Both threads save to and read from the same file through this class,
 * so the file holds the combined entries of both threads.
 *
 *
 * @author Jesse Da Silva
 * @since 15/10/2026
 */
public class RentalStorage {

    public static final String PATH = "c:\\CIS2232\\";
    public static final String FILE_NAME = "data_dasilva_jesse.json";

    private static final Path FILE = Paths.get(PATH + FILE_NAME);
    private static final Gson GSON = new Gson();

    /**
     * Static accumulator: number of orders added in this run by BOTH threads
     */
    private static final AtomicInteger ORDERS_ADDED = new AtomicInteger(0);

    private RentalStorage() {
    }

    /**
     * Append an order to the file (creating the CIS2232 folder and file if needed)
     * and update the accumulator.
     *
     * @param order order to save
     * @return number of orders added so far in this run, by both threads combined
     * @throws IOException if the file can't be written
     */
    public static synchronized int save(VehicleRentalOrder order) throws IOException {

        Path folder = FILE.getParent();
        if (folder != null) {
            Files.createDirectories(folder); // does nothing if the folder already exists
        }

        Files.writeString(FILE, order.toJson() + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        return ORDERS_ADDED.incrementAndGet();
    }

    /**
     * Read every order from the file
     *
     * @return all saved orders
     * @throws IOException if the file can't be read
     */
    public static synchronized List<VehicleRentalOrder> readAll() throws IOException {

        List<VehicleRentalOrder> orders = new ArrayList<>();

        if (!Files.exists(FILE)) {
            return orders;
        }

        for (String line : Files.readAllLines(FILE)) {
            if (!line.isBlank()) {
                orders.add(GSON.fromJson(line, VehicleRentalOrder.class));
            }
        }
        return orders;
    }

    /**
     * @return orders added in this run by both threads combined
     */
    public static int getOrdersAdded() {
        return ORDERS_ADDED.get();
    }

    /**
     * @return total orders in the file
     */
    public static int countOrdersInFile() {
        try {
            return readAll().size();
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Make new sale IDs continue after the highest ID already in the file,
     * so IDs don't repeat from one run of the program to the next.
     */
    public static void syncIdCounter() {
        try {
            for (VehicleRentalOrder order : readAll()) {
                VehicleRentalOrder.COUNTER.accumulateAndGet(order.getSaleID(), Math::max);
            }
        } catch (IOException e) {
            System.out.println("Could not read existing orders to set the next sale ID");
        }
    }
}
