package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;

public interface VehicleService {

    VehicleModel getVehicleModel();

    Vehicle addVehicle(AddVehicleRequest addVehicleRequest);

    Booking bookVehicle(BookingRequest bookingRequest);

    Double computePrice(Vehicle vehicle, BookingRequest bookingRequest);

    Double computePrice();
}
