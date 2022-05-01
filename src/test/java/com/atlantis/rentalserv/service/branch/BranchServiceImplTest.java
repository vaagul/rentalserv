package com.atlantis.rentalserv.service.branch;

import com.atlantis.rentalserv.AbstractTest;
import com.atlantis.rentalserv.controller.BookingController;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.enums.VehicleStatus;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.entity.VehicleType;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.utils.exceptions.InvalidTransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({BranchServiceImpl.class})
public class BranchServiceImplTest {

    @Autowired
    BranchServiceImpl branchService;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @BeforeEach
    public void setUpBeforeEach() {
    }

    @Test
    @Transactional
    public void addNewBranch(){
        AddBranchRequest addBranchRequest = new AddBranchRequest("b3", "BIKE,CAR");
        Branch branch = branchService.addBranch(addBranchRequest);
        Branch dbBranch = branchRepository.findByBranchName("b3").get(0);
        assertEquals(branch.getBranchName(), dbBranch.getBranchName());
        assertEquals(vehicleTypeRepository.findByBranch(dbBranch).size(), 2);
    }

    @Test
    @Transactional
    public void addExistingBranch(){
        AddBranchRequest addBranchRequest = new AddBranchRequest("b4", "BIKE,CAR");
        Branch branchOne = branchService.addBranch(addBranchRequest);
        Branch branchTwo = branchService.addBranch(addBranchRequest);
        assertEquals(branchRepository.findByBranchName("b4").size(), 2);
    }

    @Test
    @Transactional
    public void addBranchInvalidFormat(){
        AddBranchRequest addBranchRequest = new AddBranchRequest("b5", "BIKE,CARs");
        assertThrows(InvalidTransactionException.class, () -> branchService.addBranch(addBranchRequest));
    }

    @Test
    @Transactional
    public void addNullBranch(){
        AddBranchRequest addBranchRequest = new AddBranchRequest(null, "BIKE,CAR");
        assertThrows(DataIntegrityViolationException.class, () -> branchService.addBranch(addBranchRequest));
    }

    @AfterEach
    public void teardownAfterEach() {
    }

}