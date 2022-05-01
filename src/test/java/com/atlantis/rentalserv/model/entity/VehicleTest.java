package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.db.BookingRepository;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleRepository;
import com.atlantis.rentalserv.db.VehicleTypeRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
import com.atlantis.rentalserv.enums.PaymentType;
import com.atlantis.rentalserv.enums.VehicleModel;
import com.atlantis.rentalserv.enums.VehicleStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VehicleTest {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @BeforeEach
    public void setUpBeforeEach() {
        Branch branch = Branch.builder().id(null).address("here").branchName("b1").status(BranchStatus.OPEN).build();
        branchRepository.save(branch);
        vehicleTypeRepository.save(VehicleType.builder().vehicleModel(VehicleModel.CAR).branch(branch).build());
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(VehicleModel.CAR)
                .vehicleName("c1")
                .price(500D)
                .branch(branch)
                .currentVehicleStatus(VehicleStatus.OPEN)
                .build();
        vehicleRepository.save(vehicle);
    }

    @AfterEach
    public void teardownAfterEach() {
    }

//    @Test
//    @Transactional
//    public void vehicleDataIntegrityTest() {
//        Vehicle vehicle = vehicleRepository.getById(1L);
//        Booking booking = Booking.builder()
//                .vehicle(vehicle)
//                .startTime(dateUtils.getDateFromHour(1))
//                .endTime(dateUtils.getDateFromHour(4))
//                .bookedAt(dateUtils.getCurrentTime())
//                .estimatedPrice(null)
//                .paymentType(PaymentType.NETBANKING)
//                .transactionNumber("XXXX")
//                .build();
//        assertThrows(DataIntegrityViolationException.class, () -> bookingRepository.save(booking));
//    }

    @Test
    @Transactional
    public void vehicleSaveTest() {
        Branch branch = branchRepository.findByBranchName("b1").get(0);
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(VehicleModel.CAR)
                .vehicleName("c1")
                .price(500D)
                .branch(branch)
                .currentVehicleStatus(VehicleStatus.OPEN)
                .build();
        vehicleRepository.save(vehicle);
        Vehicle dbVehicle = vehicleRepository.findAll().get(0);
        assertEquals(dbVehicle.getPrice(), 500D);
    }

}
