package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.request.BookingRequest;
import com.atlantis.rentalserv.resource.BookingResource;
import com.atlantis.rentalserv.service.booking.BookingService;
import com.atlantis.rentalserv.service.booking.BookingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class BookingController implements BookingResource {

    @Autowired
    BookingServiceImpl bookingService;

    @Override
    public List<Booking> getBooking(Long id) {
        return null;
    }

    @Override
    public Booking bookVehicle(BookingRequest bookingRequest) {
        return bookingService.bookVehicle(bookingRequest);
    }
}
