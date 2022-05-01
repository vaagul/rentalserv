package com.atlantis.rentalserv.service.booking;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceFactory;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({BookingServiceImpl.class})
public class BookingServiceImplTest {
    @Autowired
    BranchServiceImpl branchService;

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @BeforeEach
    public void setUpBeforeEach() {
        AddBranchRequest addBranchRequest = new AddBranchRequest("b7", "BIKE,CAR");
        Branch branch = branchService.addBranch(addBranchRequest);
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest("b7", VehicleModel.CAR, "C7", 500D);
        VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(addVehicleRequest.getVehicleModel());
        Vehicle vehicle = vehicleRentalService.addVehicle(addVehicleRequest);
    }

    @Test
    public void testBooking() {
        BookingRequest bookingRequest = new BookingRequest("b7", VehicleModel.CAR, 1, 3);
        Booking booking = bookingService.bookVehicle(bookingRequest);
        assertNotNull(booking.getId());
    }

    @Test
    public void testBookingNonExistantVehicle(){
        BookingRequest bookingRequest = new BookingRequest("b7", VehicleModel.VAN, 1, 3);
        Booking booking = bookingService.bookVehicle(bookingRequest);
        assertNull(booking);
    }

    @Test
    public void testBookingInvalidTime(){
        BookingRequest bookingRequest = new BookingRequest("b7", VehicleModel.VAN, 3, 1);
        Booking booking = bookingService.bookVehicle(bookingRequest);
        assertNull(booking);
    }


    @AfterEach
    public void teardownAfterEach() {
    }

}
