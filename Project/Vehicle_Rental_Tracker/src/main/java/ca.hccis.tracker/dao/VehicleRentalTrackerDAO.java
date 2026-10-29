package ca.hccis.tracker.dao;

import ca.hccis.tracker.entity.VehicleRentalTrackerData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DAO class to access database
 *
 * @author Jesse Da Silva
 * @since 15/10/2026
 */

public class VehicleRentalTrackerDAO {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRentalTrackerDAO.class);

    private final String connectionString;
    private final String username;
    private final String password;

    public VehicleRentalTrackerDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        this.connectionString = rb.getString("spring.datasource.url");
        this.username = rb.getString("spring.datasource.username");
        this.password = rb.getString("spring.datasource.password");
    }

    //Open new database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    /**
     * Select all rentals
     *
     * @return list of all rentals
     */


    public ArrayList<VehicleRentalTrackerData> selectAll() {
        ArrayList<VehicleRentalTrackerData> rentals = new ArrayList<>();
        String sql = "SELECT * FROM vehiclerentaltrackerdata";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            rentals = loadList(rs);
        } catch (SQLException e) {
            logger.error("Error selecting all rentals", e);
        }
        return rentals;
    }

    /**
     * Select all by customer first name and last name
     *
     * @param firstName customer first name
     * @param lastName  customer last name
     * @return list of matching rentals
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    public ArrayList<VehicleRentalTrackerData> selectAllByCustomerName(String firstName, String lastName) {
        ArrayList<VehicleRentalTrackerData> rentals = new ArrayList<>();
        String sql = "SELECT * FROM vehiclerentaltrackerdata WHERE custFName = ? AND custLName = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            try (ResultSet rs = ps.executeQuery()) {
                rentals = loadList(rs);
        }
    } catch(SQLException e) {
        logger.error("Error selecting rentals by customer name", e);
    }
    return rentals;
}

    /**
     * Convert each row of result set into a VehicleRentalTrackerData object
     * @param rs result set
     * @return list of rentals
     * @throws SQLException
     */

    public ArrayList<VehicleRentalTrackerData> loadList(ResultSet rs) throws SQLException {
        ArrayList<VehicleRentalTrackerData> rentals = new ArrayList();

        while (rs.next()) {
            VehicleRentalTrackerData rental = new VehicleRentalTrackerData();

            rental.setSaleID(rs.getInt("saleID"));
            rental.setRentDate(rs.getString("rentDate"));
            rental.setCustFName(rs.getString("custFName"));
            rental.setCustLName(rs.getString("custLName"));
            rental.setCustAddress(rs.getString("custAddress"));
            rental.setVehicleType(rs.getString("vehicleType"));
            rental.setVehicleColour(rs.getString("vehicleColour"));
            rental.setRentalTimeDays(rs.getInt("rentalTimeDays"));
            rental.setSaleTotal(rs.getDouble("saleTotal"));

            rentals.add(rental);
        }
        return rentals;
    }
}
