package ca.hccis.tracker.controllers;

import ca.hccis.tracker.bo.VehicleRentalTrackerBO;
import ca.hccis.tracker.dao.VehicleRentalTrackerDAO;
import ca.hccis.tracker.entity.ReportTracker;
import ca.hccis.tracker.entity.VehicleRentalTrackerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Controller to administer reports of the project
 *
 * @author Jesse Da Silva
 * @since 15/10/2026
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view
     *
     * @param model
     * @param session
     * @return To appropriate view
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * Method to send user to customer name report
     *
     * @param model
     * @return view for list
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    @RequestMapping("/tracker/name")
    public String reportTrackerName(Model model) {
        logger.info("Running the reports controller tracker name method");
        model.addAttribute("reportInput", new ReportTracker());
        return "report/reportTrackerName";
    }

    /**
     * Method to Send user to vehicle rental information report
     *
     * @param model
     * @return view for list
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    @RequestMapping("/tracker/rental")
    public String reportTrackerRental(Model model) {
        logger.info("Running the reports controller rental info method");
        model.addAttribute("reportInput", new ReportTracker());
        return "report/reportTrackerRental";
    }

    /**
     * Process name report
     *
     * @param model
     * @param reportTracker Object containing inputs for the report
     * @return view to show report
     * @author Jesse Da Silva
     * @since 15/10/2026
     */

    @RequestMapping("/tracker/name/submit")
    public String reportTrackerNameSubmit(Model model, @ModelAttribute("reportInput") ReportTracker reportTracker) {

        System.out.println("Name from input form: " + reportTracker.getfName() + " " + reportTracker.getlName());

        VehicleRentalTrackerBO vehicleRentalTrackerBO = new VehicleRentalTrackerBO();
        ArrayList<VehicleRentalTrackerData> list = vehicleRentalTrackerBO.processSelectAllByCustomerName(reportTracker.getfName() + " " + reportTracker.getlName());
        reportTracker.setVehicleRentalTrackerData(list);

        if (list != null && list.isEmpty()) {
            model.addAttribute("message", "no rentals found under that name");
            System.out.println("no data found");
        }

        model.addAttribute("reportInput", reportTracker);

        return "report/reportTrackerName";
    }
}
