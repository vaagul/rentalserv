package com.atlantis.rentalserv.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddBranchRequest {
    @NotBlank(message = "branchName is mandatory")
    String branchName;
    @NotBlank(message = "vehicleTypes is mandatory")
    String vehicleTypes;
}
