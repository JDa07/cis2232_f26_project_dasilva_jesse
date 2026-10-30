package ca.hccis.tracker.repositories;

import ca.hccis.tracker.entity.VehicleRentalTrackerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository <VehicleRentalTrackerData, Integer>{

}
