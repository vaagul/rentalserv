package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class VehicleType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Long id;

    @Enumerated(EnumType.STRING)
    VehicleModel vehicleModel;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonBackReference
    Branch branch;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public String getBranch() {
        return branch.getBranchName();
    }

}
