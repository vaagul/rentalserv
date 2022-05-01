package com.atlantis.rentalserv.resource;

import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookingResource {
    @GetMapping("/booking/{bookingId}")
    List<Booking> getBooking(@PathVariable(value = "bookingId") Long id);

    @PostMapping("/booking")
    Booking bookVehicle(@RequestBody BookingRequest bookingRequest);
}
