package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.BookingRequest;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl extends VehicleServiceImpl {

    // Ideal way is to get from DbConfig
    public static final Double surgeIncreaseInPercent = 1.10;

    @Override
    public VehicleModel getVehicleModel() {
        return VehicleModel.BUS;
    }

    @Override
    public Double computePrice(Vehicle vehicle, BookingRequest bookingRequest) {
        if(getDemandPercentage(vehicle, bookingRequest) <= 0.2D)
            return vehicle.getPrice() * surgeIncreaseInPercent;
        return vehicle.getPrice();
    }

    @Override
    public Double computePrice() {
        return null;
    }


}
