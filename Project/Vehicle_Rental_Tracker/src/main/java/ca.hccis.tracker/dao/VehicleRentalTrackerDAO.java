package ca.hccis.tracker.dao;

import ca.hccis.tracker.entity.VehicleRentalTrackerData;

import ca.hccis.tracker.util.CisUtilityFile;
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

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(VehicleRentalTrackerDAO.class);

    public VehicleRentalTrackerDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }

    public ArrayList<VehicleRentalTrackerData> selectAll() {
        ArrayList<VehicleRentalTrackerData> rentals = null;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from vehiclerentaltrackerdata;");

            rentals = new ArrayList();

            while (rs.next()) {

                VehicleRentalTrackerData rental = new VehicleRentalTrackerData();

                rental.setSaleID(rs.getInt("saleID"));
                rental.setRentDate(rs.getString("rentDate"));
                rental.setCustFName(rs.getString("custFName"));
                rental.setCustLName(rs.getString("custLName"));
                rental.setVehicleType(rs.getString("vehicleType"));
                rental.setVehicleColour(rs.getString("vehicleColour"));
                rental.setRentalTimeDays(rs.getInt("rentalTimeDays"));
                rental.setSaleTotal(rs.getDouble("saleTotal"));

                rentals.add(rental);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                stmt.close();

            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        } return rentals;
    }

    /**
     * Select all by customer name
     *
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    public ArrayList<VehicleRentalTrackerData> selectAllByCustomerName(String fName, String lName) throws SQLException {
        ArrayList<VehicleRentalTrackerData> rentals = null;
        String sql = "SELECT * FROM VehicleRentalTrackerData WHERE custFName = ? AND custLName = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, fName);
            ps.setString(2, lName);
            try (ResultSet results = ps.executeQuery()) {
                rentals = loadList(results);
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
        return rentals;
    }

    public ArrayList<VehicleRentalTrackerData> processSelectAllByCustomerName(String fName, String lName) throws SQLException {

        VehicleRentalTrackerDAO vehicleRentalTrackerDAO = new VehicleRentalTrackerDAO();
        ArrayList<VehicleRentalTrackerData> rentals =
                vehicleRentalTrackerDAO.selectAllByCustomerName(fName, lName);

        if (rentals != null) {
            CisUtilityFile.writeReportToFile("customerNameReport", rentals);
        }
        return rentals;
    }

    public ArrayList<VehicleRentalTrackerData> loadList(ResultSet rs) throws SQLException {
        ArrayList<VehicleRentalTrackerData> rentals = new ArrayList();

        while (rs.next()) {
            VehicleRentalTrackerData rental = new VehicleRentalTrackerData();

            rental.setSaleID(rs.getInt("saleID"));
            rental.setRentDate(rs.getString("rentDate"));
            rental.setCustFName(rs.getString("custFName"));
            rental.setCustLName(rs.getString("custLName"));
            rental.setVehicleType(rs.getString("vehicleType"));
            rental.setVehicleColour(rs.getString("vehicleColour"));
            rental.setRentalTimeDays(rs.getInt("rentalTimeDays"));
            rental.setSaleTotal(rs.getDouble("saleTotal"));

            rentals.add(rental);
        } return rentals;
    }
}
