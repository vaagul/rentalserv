package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.db.BookingRepository;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.VehicleStatus;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.entity.VehicleType;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;
import com.atlantis.rentalserv.service.booking.BookingServiceImpl;
import com.atlantis.rentalserv.utils.DateUtils;
import com.atlantis.rentalserv.utils.constants.CommandOrchestratorConstants;
import com.atlantis.rentalserv.utils.exceptions.InvalidTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public abstract class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DateUtils dateUtils;

    @Transactional
    public Double getDemandPercentage(Vehicle vehicle, BookingRequest bookingRequest){
        Branch branch = branchRepository.findByBranchName(bookingRequest.getBranchName()).get(0);
        List<Vehicle> vehiclesPerBranchAndModel = branch.getVehicles().stream().filter(_vehicle -> _vehicle.getVehicleModel() == bookingRequest.getVehicleModel()).collect(Collectors.toList());
        List<Vehicle> availableVehiclePerBranch = new ArrayList<>();
        vehiclesPerBranchAndModel.parallelStream().forEach(_vehicle -> {
            List<Booking> activeBooking = bookingRepository.findByVehicleAndStartTimeAfterOrEndTimeBefore(_vehicle, dateUtils.getDateFromHour(bookingRequest.getStartTime()), dateUtils.getDateFromHour(bookingRequest.getEndTime()));
            if(activeBooking != null && activeBooking.size()==0){
                availableVehiclePerBranch.add(_vehicle);
            }
        });
        return (double)availableVehiclePerBranch.size()/(double) vehiclesPerBranchAndModel.size();
    }

    @Override
    @Transactional
    public Vehicle addVehicle(AddVehicleRequest addVehicleRequest) {
        // Add Validation. Branch not found.
        Branch branch = branchRepository.findByBranchName(addVehicleRequest.getBranchName()).get(0);
        List<VehicleType> allowedVehicleTypesInBranch = vehicleTypeRepository.findByBranchAndVehicleModel(branch, addVehicleRequest.getVehicleModel());
        if(Objects.nonNull(allowedVehicleTypesInBranch) && allowedVehicleTypesInBranch.size() == 0){
            log.info(CommandOrchestratorConstants.COMMAND_OUTPUT_FALSE);
//            throw new InvalidTransactionException(addVehicleRequest.getVehicleModel() + " is not allowed for this Branch");
            log.debug(addVehicleRequest.getVehicleModel() + " is not allowed for this Branch");
            return null;
        }
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(addVehicleRequest.getVehicleModel())
                .vehicleName(addVehicleRequest.getVehicleName())
                .price(addVehicleRequest.getPrice())
                .branch(branch)
                .currentVehicleStatus(VehicleStatus.OPEN)
                .build();

        vehicleRepository.save(vehicle);
        log.info(CommandOrchestratorConstants.COMMAND_OUTPUT_TRUE);
        return vehicle;
    }

    @Override
    public Booking bookVehicle(BookingRequest bookingRequest) {
        return null;
    }


}
