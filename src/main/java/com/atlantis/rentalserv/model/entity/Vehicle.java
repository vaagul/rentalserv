package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    VehicleModel vehicleModel;

    @Column
    String vehicleName;

    @Column
    Double price;

    @Enumerated(EnumType.STRING)
    VehicleStatus currentVehicleStatus;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    Branch branch;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public String getBranch() {
        return branch.getBranchName();
    }

}
