package com.atlantis.rentalserv.service.branch;

import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BranchService {
    List<Vehicle> getBranchVehicles(String branchName, int startTime, int endTime, VehicleModel vehicleModel);

    List<Branch> getBranch(String branchName);
    Branch addBranch(AddBranchRequest addBranchRequest);
    void deleteBranch();
    void addVehicleTypeToBranch();
    void setBranchStatus(BranchStatus branchStatus);
}
