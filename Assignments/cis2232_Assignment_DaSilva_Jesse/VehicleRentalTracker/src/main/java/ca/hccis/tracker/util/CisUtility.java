package ca.hccis.tracker.util;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * Has some useful methods to be used in our programs.
 * <p>
 * An instance works in one of two modes, chosen when it is created:
 * console (System.in / System.out) or GUI (JOptionPane dialogs). Giving each
 * thread its own instance lets one thread use the console while another uses
 * JOptionPane at the same time.
 *
 * @author bjmaclean
 * @since Oct 19, 2021
 */
public class CisUtility {

    private static final String TITLE = "Vehicle Rental Tracker";

    // One console reader shared by every instance
    private static final Scanner input = new Scanner(System.in);

    private final boolean useGUI;

    /**
     * Console mode
     */
    public CisUtility() {
        this(false);
    }

    /**
     * @param useGUI true to use JOptionPane dialogs, false to use the console
     */
    public CisUtility(boolean useGUI) {
        this.useGUI = useGUI;
    }

    public boolean isUseGUI() {
        return useGUI;
    }

    /**
     * Return the default currency String value of the double passed in as a
     * parameter.
     *
     * @param inputDouble double to be formatted
     * @return String in default currency format
     * @author BJM
     * @since 20211020
     */
    public static String toCurrency(double inputDouble) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(inputDouble);
    }

    /**
     * Get input from the user (console or JOptionPane)
     *
     * @param prompt Prompt for the user
     * @return String entered by the user, or null if a JOptionPane dialog was cancelled
     * @author BJM
     * @since 20211020
     */
    public String getInputString(String prompt) {

        if (useGUI) {
            return JOptionPane.showInputDialog(null, prompt, TITLE, JOptionPane.QUESTION_MESSAGE);
        }

        System.out.println(prompt + " -->");
        return input.nextLine();
    }

    /**
     * Get input from the user, repeating until the length is acceptable
     *
     * @param prompt    Prompt for the user
     * @param minLength minimum length
     * @param maxLength maximum length
     * @return String entered by the user, or null if a JOptionPane dialog was cancelled
     * @author BJM
     * @since 20211020
     */
    public String getInputString(String prompt, int minLength, int maxLength) {

        String output = getInputString(prompt + " (" + minLength + " to " + maxLength + " characters)");

        while (output != null && (output.length() < minLength || output.length() > maxLength)) {
            output = getInputString(prompt + " (" + minLength + " to " + maxLength + " characters)");
        }

        return output;
    }

    /**
     * Get a double from the user, repeating until the input is a number
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user (0 if a JOptionPane dialog was cancelled)
     * @author BJM
     * @since 20211020
     */
    public double getInputDouble(String prompt) {

        while (true) {
            String inputString = getInputString(prompt);
            if (inputString == null) {
                return 0;
            }
            try {
                return Double.parseDouble(inputString.trim());
            } catch (NumberFormatException e) {
                display("Please enter a number.");
            }
        }
    }

    /**
     * Get an int from the user, repeating until the input is a whole number
     *
     * @param prompt Prompt for the user
     * @return The int entered by the user (0 if a JOptionPane dialog was cancelled)
     * @author BJM
     * @since 20211020
     */
    public int getInputInt(String prompt) {

        while (true) {
            String inputString = getInputString(prompt);
            if (inputString == null) {
                return 0;
            }
            try {
                return Integer.parseInt(inputString.trim());
            } catch (NumberFormatException e) {
                display("Please enter a whole number.");
            }
        }
    }

    /**
     * Get input boolean from the user
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @author BJM
     * @since 20211108
     */
    public boolean getInputBoolean(String prompt) {
        return getInputBoolean(prompt, "y", "n");
    }

    /**
     * Get input boolean from the user
     *
     * @param prompt      Prompt for the user
     * @param affirmative text that means true
     * @param negative    text that means false
     * @return boolean as specified by user input
     * @author BJM
     * @since 20211108
     */
    public boolean getInputBoolean(String prompt, String affirmative, String negative) {

        String inputString = getInputString(prompt + " (" + affirmative + "/" + negative + ")");
        return inputString != null && inputString.equalsIgnoreCase(affirmative);
    }

    /**
     * Show a message to the user (console or JOptionPane)
     *
     * @param message text to show
     */
    public void display(String message) {

        if (!useGUI) {
            System.out.println(message);
            return;
        }

        // long messages (like the list of all orders) get a scroll pane
        if (message.split("\\R").length > 15) {
            JTextArea area = new JTextArea(message);
            area.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(area);
            scrollPane.setPreferredSize(new Dimension(450, 300));
            JOptionPane.showMessageDialog(null, scrollPane, TITLE, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message, TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Provide today's date in the specified format
     *
     * @param format Date format desired
     * @return Today's date in specified format
     * @author BJM
     * @since 20211021
     */
    public static String getTodayString(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Get a random number between min and max
     *
     * @author BJM
     * @since 20211109
     */
    public static int getRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}