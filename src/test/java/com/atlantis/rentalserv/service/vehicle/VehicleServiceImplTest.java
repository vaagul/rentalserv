package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.service.booking.BookingServiceImpl;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
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
public class VehicleServiceImplTest {
    @Autowired
    BranchServiceImpl branchService;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @BeforeEach
    public void setUpBeforeEach() {
        AddBranchRequest addBranchRequest = new AddBranchRequest("b6", "BIKE,CAR");
        Branch branch = branchService.addBranch(addBranchRequest);
    }

    @Test
    public void addVehicle(){
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest("b6", VehicleModel.CAR, "C6", 500D);
        VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(addVehicleRequest.getVehicleModel());
        Vehicle vehicle = vehicleRentalService.addVehicle(addVehicleRequest);
        assertNotNull(vehicle);
    }

    @Test
    public void addNonExistentVehicle(){
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest("b6", VehicleModel.TRUCK, "C6", 500D);
        VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(addVehicleRequest.getVehicleModel());
        Vehicle vehicle = vehicleRentalService.addVehicle(addVehicleRequest);
        assertNull(vehicle);
    }

    @AfterEach
    public void teardownAfterEach() {
    }
}
