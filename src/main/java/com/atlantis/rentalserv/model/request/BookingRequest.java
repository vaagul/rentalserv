package com.atlantis.rentalserv.model.request;

import com.atlantis.rentalserv.enums.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    String branchName;
    VehicleModel vehicleModel;
    int startTime;
    int endTime;
}
