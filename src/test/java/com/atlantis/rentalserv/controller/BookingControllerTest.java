package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.AbstractTest;
import com.atlantis.rentalserv.controller.BookingController;
import com.atlantis.rentalserv.db.BookingRepository;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.enums.VehicleStatus;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.entity.VehicleType;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({BookingController.class})
public class BookingControllerTest extends AbstractTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private BookingController bookingController;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    BranchRepository branchRepository;

    @BeforeEach
    public void setUpBeforeEach() {
        Branch branch = Branch.builder().id(null).address("here").branchName("b5").status(BranchStatus.OPEN).build();
        branchRepository.save(branch);
        vehicleTypeRepository.save(VehicleType.builder().vehicleModel(VehicleModel.CAR).branch(branch).build());
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(VehicleModel.CAR)
                .vehicleName("C5")
                .price(500D)
                .branch(branch)
                .currentVehicleStatus(VehicleStatus.OPEN)
                .build();
        vehicleRepository.save(vehicle);
    }

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(bookingController);
    }

    @Test
    public void createBookingTests() throws Exception {
        String uri = "/booking";
        BookingRequest bookingRequest = new BookingRequest("b5", VehicleModel.CAR, 1, 5);
        String inputJson = super.mapToJson(bookingRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("bookedAt"));
    }

    @AfterEach
    public void teardownAfterEach() {
    }

}
