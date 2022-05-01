package com.atlantis.rentalserv.resource;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface BranchResource {
    @GetMapping("/branch/{branchName}")
    List<Branch> getBranch(@PathVariable(value = "branchName") String branchName);

    @GetMapping("/branch/{branchName}/vehicles")
    List<Vehicle> getBranchVehicles(@PathVariable(value = "branchName") String branchName,
                                    @RequestParam(name = "startTime", required = false) int startTime,
                                    @RequestParam(name = "endTime", required = false) int endTime,
                                    @RequestParam(name = "vehicleModel", required = false) VehicleModel vehicleModel);

    @PostMapping("/branch")
    Branch addBranch(@RequestBody @Valid AddBranchRequest addBranchRequest);

}
