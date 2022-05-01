package com.atlantis.rentalserv.service.vehicle;

import com.atlantis.rentalserv.enums.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceFactory {

    @Value("${default.vehicleClient}")
    private VehicleModel defaultVehicleClient;

    @Autowired
    private List<VehicleServiceImpl> vehicleRentalServices;

    private static final Map<VehicleModel, VehicleServiceImpl> vehicleServiceCache = new HashMap<>();

    @PostConstruct
    public void initVehicleServiceCache() {
        vehicleRentalServices.forEach(vehicleModel -> vehicleServiceCache.put(vehicleModel.getVehicleModel(), vehicleModel));
    }

    public VehicleServiceImpl getVehicleClient(VehicleModel vehicleModel){
        return vehicleServiceCache.getOrDefault(vehicleModel, vehicleServiceCache.get(defaultVehicleClient));
    }
}
