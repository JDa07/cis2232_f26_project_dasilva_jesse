package ca.hccis.tracker.controllers;

import ca.hccis.tracker.bo.VehicleRentalTrackerBO;
import ca.hccis.tracker.entity.VehicleRentalTrackerData;
import ca.hccis.tracker.repositories.RentalRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final RentalRepository repository;

    public BaseController(RentalRepository repository) {
        this.repository = repository;
    }

    /**
     *
     * Drop-down of vehicle types on add form
     */
    @ModelAttribute("vehicleTypes")
    public List<String> vehicleTypes() {
        return List.of("Car", "Truck", "Electric", "Van");
    }

    /**
     * Home page (rentals chart)
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("rentals", repository.findAll());
        return "index";
    }

    /**
     * List all rentals
     */
    @RequestMapping("/vehiclerentaltracker")
    public String list(Model model){
        logger.info("Listing all rentals");
        model.addAttribute("rentals", repository.findAll());
        return "vehiclerentaltracker/list";
    }

    /**
     * Show empty form to add rental
     */
    @RequestMapping("/vehiclerentaltracker/add")
    public String add(Model model){
        model.addAttribute("rental",  new VehicleRentalTrackerData());
        return "vehiclerentaltracker/add";
    }
}
