package com.atlantis.rentalserv.model.request;

import com.atlantis.rentalserv.enums.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVehicleRequest {
    String branchName;
    VehicleModel vehicleModel;
    String vehicleName;
    Double price;
}
