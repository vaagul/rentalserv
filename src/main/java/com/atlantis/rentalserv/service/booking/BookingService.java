package com.atlantis.rentalserv.service.booking;

import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.request.BookingRequest;

public interface BookingService {

    public Booking bookVehicle(BookingRequest bookingRequest);
}
