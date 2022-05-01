package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.resource.BranchResource;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class BranchController implements BranchResource {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchServiceImpl branchServiceImpl;

    @Override
    public List<Branch> getBranch(String branchName) {
        return branchServiceImpl.getBranch(branchName);
    }

    @Override
    public List<Vehicle> getBranchVehicles(String branchName, int startTime, int endTime, VehicleModel vehicleModel) {
        return branchServiceImpl.getBranchVehicles(branchName, startTime, endTime, vehicleModel);
    }

    @Override
    public Branch addBranch(@Valid AddBranchRequest addBranch) {
        return branchServiceImpl.addBranch(addBranch);
    }

}
