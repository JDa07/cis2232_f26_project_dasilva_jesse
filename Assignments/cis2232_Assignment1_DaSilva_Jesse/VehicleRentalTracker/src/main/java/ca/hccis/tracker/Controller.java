package ca.hccis.tracker;

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
 * Controls the program
 *
 * @author Jesse Da Silva
 * @since 22-09-2026
 */

public class Controller {


    public static final String
    MENU = "1) Add Order" + System.lineSeparator()
            + "2) View Orders" + System.lineSeparator()
            + "X) Exit" + System.lineSeparator();

    static void main(String[] args) {

        System.out.println("Hello World");

    }
}
