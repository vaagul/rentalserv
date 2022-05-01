package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.AbstractTest;
import com.atlantis.rentalserv.controller.VehicleController;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
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
@Import({VehicleController.class})
public class VehicleControllerTest extends AbstractTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchServiceImpl branchService;

    @BeforeEach
    public void setUpBeforeEach() {
        AddBranchRequest addBranchRequest = new AddBranchRequest("b1", "BIKE,CAR");
        Branch branch = branchService.addBranch(addBranchRequest);
    }

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(vehicleController);
    }

    @Test
    public void createVehicleTests() throws Exception {
        String uri = "/vehicle";
        AddVehicleRequest addVehicleRequest = new AddVehicleRequest("b1", VehicleModel.CAR, "C1", 500D);
        String inputJson = super.mapToJson(addVehicleRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("C1"));
    }

    @AfterEach
    public void teardownAfterEach() {
    }


}
