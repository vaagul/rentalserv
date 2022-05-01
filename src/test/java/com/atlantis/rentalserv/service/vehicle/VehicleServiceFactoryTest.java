package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.VehicleModel;
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
@Import({VehicleServiceFactory.class})
public class VehicleServiceFactoryTest {

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @BeforeEach
    public void setUpBeforeEach() {
    }

    @Test
    public void testBikeServiceImpl(){
        assertTrue(vehicleServiceFactory.getVehicleClient(VehicleModel.BIKE) instanceof BikeServiceImpl);
    }

    @Test
    public void testBusServiceImpl(){
        assertTrue(vehicleServiceFactory.getVehicleClient(VehicleModel.BUS) instanceof BusServiceImpl);
    }

    @Test
    public void testCarServiceImpl(){
        assertTrue(vehicleServiceFactory.getVehicleClient(VehicleModel.CAR) instanceof CarServiceImpl);
    }

    @Test
    public void testTruckServiceImpl(){
        assertTrue(vehicleServiceFactory.getVehicleClient(VehicleModel.TRUCK) instanceof TruckServiceImpl);
    }

    @Test
    public void testVanServiceImpl(){
        assertTrue(vehicleServiceFactory.getVehicleClient(VehicleModel.VAN) instanceof VanServiceImpl);
    }

    @AfterEach
    public void teardownAfterEach() {
    }
}
