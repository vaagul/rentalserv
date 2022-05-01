package com.atlantis.rentalserv.db;

import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByVehicle(Vehicle vehicle);

    @Query(value = "select b from Booking b where b.vehicle=:vehicle AND (b.startTime<=:endTime OR b.endTime<=:startTime)")
    List<Booking> findByVehicleAndStartTimeAfterOrEndTimeBefore(@Param("vehicle") Vehicle vehicle,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    List<Booking> findByTransactionNumber(String transactionNumber);
}
