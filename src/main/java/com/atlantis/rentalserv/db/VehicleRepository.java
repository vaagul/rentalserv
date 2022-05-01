package com.atlantis.rentalserv.db;

import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBranchOrderByPrice(Branch branch);
}
