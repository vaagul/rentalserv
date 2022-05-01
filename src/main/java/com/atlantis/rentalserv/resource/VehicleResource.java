package com.atlantis.rentalserv.resource;

import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.AddVehicleRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VehicleResource {
    @GetMapping("/vehicle/{vehicleName}")
    List<Vehicle> getVehicle(@PathVariable(value = "vehicleName") String vehicleName);

    @PostMapping("/vehicle")
    Vehicle addVehicle(@RequestBody AddVehicleRequest addVehicleRequest);
}
