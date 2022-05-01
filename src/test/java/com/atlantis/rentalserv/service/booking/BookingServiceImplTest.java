package com.atlantis.rentalserv.service.booking;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    BranchRepository branchRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @BeforeEach
    public void setUpBeforeEach() {
    }



    @AfterEach
    public void teardownAfterEach() {
    }

}
