package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.enums.BranchStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Long id;

    @Column
    String address;

    @Column(nullable = false)
    String branchName;

    @Enumerated(EnumType.STRING)
    BranchStatus status;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "branch", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<VehicleType> vehicleTypes;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "branch")
    List<Vehicle> vehicles;
}
