package ca.hccis.tracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CisUtilityFile {

    private static final Logger logger = LoggerFactory.getLogger(CisUtilityFile.class);

    public static final String FOLDER_NAME = "c:\\CIS2232\\";

    /**
     * Write each object to a new time-stamped report file.
     * Creates the folder if it doesn't exist.
     *
     * @param fileName   start of the report file name
     * @param theObjects objects to write, one per line
     */
    public static void writeReportToFile(String fileName, List<?> theObjects) {

        if (theObjects == null) {
            return;
        }

        Path folder = Paths.get(FOLDER_NAME);
        Path file = folder.resolve(fileName + CisUtility.getCurrentDate("_yyyyMMddHHmmss") + ".txt");

        try {
            Files.createDirectories(folder); // does nothing if the folder already exists

            try (BufferedWriter writer = Files.newBufferedWriter(file,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                for (Object current : theObjects) {
                    writer.write(current.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            logger.error("Could not write report to {}", file, e);
        }
    }
}