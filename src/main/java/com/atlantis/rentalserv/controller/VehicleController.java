package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import com.atlantis.rentalserv.resource.VehicleResource;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceFactory;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class VehicleController implements VehicleResource {

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @Override
    public List<Vehicle> getVehicle(String vehicleName) {
        return null;
    }

    @Override
    public Vehicle addVehicle(AddVehicleRequest addVehicleRequest) {
        VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(addVehicleRequest.getVehicleModel());
        return vehicleRentalService.addVehicle(addVehicleRequest);
    }
}
