package ca.hccis.tracker.bo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ca.hccis.tracker.entity.VehicleRentalTrackerData;

class VehicleRentalTrackerBOTest {

    @Test
    void calculateSaleTotal_car5Days() {
        VehicleRentalTrackerData data = new VehicleRentalTrackerData();
        data.setVehicleType("car");
        data.setRentalTimeDays(5);

        double actual = VehicleRentalTrackerBO.calculateSaleTotal(data);

        Assertions.assertEquals(201.25, actual, 0.001);
        Assertions.assertEquals(201.25, data.getSaleTotal(), 0.001);

    }

    @Test
    void calculateSaleTotal_truck3Days() {
        VehicleRentalTrackerData data = new VehicleRentalTrackerData();
        data.setVehicleType("truck");
        data.setRentalTimeDays(3);

        double actual = VehicleRentalTrackerBO.calculateSaleTotal(data);

        Assertions.assertEquals(138, actual, 0.001);
        Assertions.assertEquals(138, data.getSaleTotal(), 0.001);

    }

    @Test
    void calculateSaleTotal_electric1Days() {
        VehicleRentalTrackerData data = new VehicleRentalTrackerData();
        data.setVehicleType("electric");
        data.setRentalTimeDays(1);

        double actual = VehicleRentalTrackerBO.calculateSaleTotal(data);

        Assertions.assertEquals(51.75, actual, 0.001);
        Assertions.assertEquals(51.75, data.getSaleTotal(), 0.001);
    }

    @Test
    void calculateSaleTotal_van2Days() {
        VehicleRentalTrackerData data = new VehicleRentalTrackerData();
        data.setVehicleType("van");
        data.setRentalTimeDays(2);

        double actual = VehicleRentalTrackerBO.calculateSaleTotal(data);

        Assertions.assertEquals(112.7, actual, 0.001);
        Assertions.assertEquals(112.7, data.getSaleTotal(), 0.001);

    }

    @Test
    void calculateSaleTotal_nullType() {
        VehicleRentalTrackerData data = new VehicleRentalTrackerData();
        data.setRentalTimeDays(10);

        double actual = VehicleRentalTrackerBO.calculateSaleTotal(data);

        Assertions.assertEquals(0.0, actual, 0.001);
        Assertions.assertEquals(0.0, data.getSaleTotal(), 0.001);
    }
}