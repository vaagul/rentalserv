package com.atlantis.rentalserv.db;

import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long>{
    List<VehicleType> findByBranch(Branch branch);

    List<VehicleType> findByBranchAndVehicleModel(Branch branch, VehicleModel vehicleModel);


//    List<Booking> findByBranchName(String branchName);
}
