package ca.hccis.tracker.bo;

import ca.hccis.tracker.entity.VehicleRentalTrackerData;

public class VehicleRentalTrackerBO {
    //CONSTANTS//
    public static final int CAR_PRICE = 35;
    public static final int TRUCK_PRICE = 40;
    public static final int ELECTRIC_PRICE = 45;
    public static final int VAN_PRICE = 49;
    public static final double TAX_RATE = 1.15;

    public static Double calculateSaleTotal(VehicleRentalTrackerData data) {
        double costOfVehicle;

        if (data.getVehicleType() == null){
            data.setSaleTotal(0);
            return 0.0;
        }

        switch (data.getVehicleType().toLowerCase()) {
            case "car":
                costOfVehicle = CAR_PRICE;
                break;
            case "truck":
                costOfVehicle = TRUCK_PRICE;
                break;
            case "electric":
                costOfVehicle = ELECTRIC_PRICE;
                break;
            case "van":
                costOfVehicle = VAN_PRICE;
                break;
            default:
                costOfVehicle = 0;
        }
        double total = (costOfVehicle * data.getRentalTimeDays()) * TAX_RATE;
        data.setSaleTotal(total);
        return total;
    }
}
