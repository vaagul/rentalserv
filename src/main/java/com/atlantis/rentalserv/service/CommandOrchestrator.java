package com.atlantis.rentalserv.service;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.model.request.BookingRequest;
import com.atlantis.rentalserv.service.booking.BookingServiceImpl;
import com.atlantis.rentalserv.service.branch.BranchServiceImpl;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceFactory;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceImpl;
import com.atlantis.rentalserv.utils.constants.CommandOrchestratorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandOrchestrator {

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @Autowired
    BranchServiceImpl branchService;

    public void process(String command) {
        // This is just to run using a file. For all other flows, please use the restAPIs
        String[] commandSplit = command.split(" ", 2);
        performCommand(commandSplit[0], commandSplit[1]);
    }

    private void performCommand(String keyword, String parameter) {
        String[] parameters = parameter.split(" ");
        switch (keyword){
            case CommandOrchestratorConstants.ADD_BRANCH: {
                AddBranchRequest addBranchRequest = new AddBranchRequest(parameters[0], parameters[1]);
                branchService.addBranch(addBranchRequest);
                break;
            }
            case CommandOrchestratorConstants.ADD_VEHICLE: {
                AddVehicleRequest addVehicleRequest = new AddVehicleRequest(parameters[0], VehicleModel.valueOf(parameters[1]), parameters[2], Double.parseDouble(parameters[3]));
                VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(addVehicleRequest.getVehicleModel());
                vehicleRentalService.addVehicle(addVehicleRequest);
                break;
            }
            case CommandOrchestratorConstants.BOOK: {
                BookingRequest bookingRequest = new BookingRequest(parameters[0], VehicleModel.valueOf(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
                bookingService.bookVehicle(bookingRequest);
                break;
            }
            case CommandOrchestratorConstants.DISPLAY_VEHICLES: {
                List<Vehicle> availableVehicles = branchService.getBranchVehicles(parameters[0], Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), null );
                log.info(availableVehicles.stream().map(Vehicle::getVehicleName).collect(Collectors.joining(",")));
                break;
            }

            default:
                log.info("Invalid command " + keyword + " found");
        }

    }
}
