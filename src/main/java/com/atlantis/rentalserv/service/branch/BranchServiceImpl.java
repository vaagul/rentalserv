package com.atlantis.rentalserv.service.branch;

import com.atlantis.rentalserv.db.BookingRepository;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.entity.VehicleType;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.utils.DateUtils;
import com.atlantis.rentalserv.utils.constants.CommandOrchestratorConstants;
import com.atlantis.rentalserv.utils.exceptions.InvalidTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DateUtils dateUtils;

    private void validateAddBranchRequest(AddBranchRequest addBranchRequest) {
        try{
            List<VehicleModel> vehicleModels = Arrays.stream(addBranchRequest.getVehicleTypes().split(",")).map(VehicleModel::valueOf).collect(Collectors.toList());
        }
        catch (IllegalArgumentException ex){
            log.info(CommandOrchestratorConstants.COMMAND_OUTPUT_FALSE);
            throw new InvalidTransactionException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Vehicle> getBranchVehicles(String branchName, int startTime, int endTime, VehicleModel vehicleModel) {
        Branch branch = branchRepository.findByBranchName(branchName).get(0);
//        List<Vehicle> vehicleList = branch.getVehicles();
        List<Vehicle> vehicleList = vehicleRepository.findByBranchOrderByPrice(branch);
        List<Vehicle> avaliableVehiclesInTimeSlot = new ArrayList<>();
        vehicleList.stream().forEach(vehicle -> {
            List<Booking> activeBooking = bookingRepository.findByVehicleAndStartTimeAfterOrEndTimeBefore(vehicle, dateUtils.getDateFromHour(startTime), dateUtils.getDateFromHour(endTime));
            if(Objects.nonNull(activeBooking) && activeBooking.size()==0){
                avaliableVehiclesInTimeSlot.add(vehicle);
            }
        });
        return avaliableVehiclesInTimeSlot;
    }

    @Override
    public List<Branch> getBranch(String branchName) {
        return branchRepository.findByBranchName(branchName);
    }

    @Override
    @Transactional
    public Branch addBranch(AddBranchRequest addBranchRequest) {
        validateAddBranchRequest(addBranchRequest);
        //Validate: If Branch name already exists, If VehiclesModels are in correct format
        String[] stringVehicleTypes = addBranchRequest.getVehicleTypes().split(",");
        Branch newBranch = Branch.builder()
                .branchName(addBranchRequest.getBranchName())
                .status(BranchStatus.OPEN)
                .build();
        branchRepository.save(newBranch);
        List<VehicleType> vehicleTypes = Arrays.stream(stringVehicleTypes).map(stringVehicleType -> {
            VehicleModel vehicleModel = VehicleModel.valueOf(stringVehicleType);
            return VehicleType.builder().vehicleModel(vehicleModel).branch(newBranch).build();
        }).collect(Collectors.toList());
        vehicleTypes.forEach(vehicleType -> vehicleTypeRepository.save(vehicleType));
        log.info(CommandOrchestratorConstants.COMMAND_OUTPUT_TRUE);
        return newBranch;
    }

    @Override
    public void deleteBranch() {

    }

    @Override
    public void addVehicleTypeToBranch() {

    }

    @Override
    public void setBranchStatus(BranchStatus branchStatus) {
        // Notify stakeholders

    }
}
