package com.atlantis.rentalserv.service.booking;

import com.atlantis.rentalserv.db.BookingRepository;
import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.db.VehicleRepository;
import com.atlantis.rentalserv.enums.BookingType;
import com.atlantis.rentalserv.enums.PaymentType;
import com.atlantis.rentalserv.model.entity.Booking;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.entity.Vehicle;
import com.atlantis.rentalserv.model.request.BookingRequest;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceFactory;
import com.atlantis.rentalserv.service.vehicle.VehicleServiceImpl;
import com.atlantis.rentalserv.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService{

    @Autowired
    VehicleServiceFactory vehicleServiceFactory;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DateUtils dateUtils;

    private boolean checkVehicleAvailability(BookingRequest bookingRequest) {
        return false;
    }

    private Double getDynamicPrice(Vehicle vehicle, BookingRequest bookingRequest){
        VehicleServiceImpl vehicleRentalService = vehicleServiceFactory.getVehicleClient(bookingRequest.getVehicleModel());
        return vehicleRentalService.computePrice(vehicle, bookingRequest);
    }

    @Override
    @Transactional
    public Booking bookVehicle(BookingRequest bookingRequest) {
        Branch branch = branchRepository.findByBranchName(bookingRequest.getBranchName()).get(0);
        List<Vehicle> vehiclesPerBranchAndModel = branch.getVehicles().stream().filter(vehicle -> vehicle.getVehicleModel() == bookingRequest.getVehicleModel()).collect(Collectors.toList());
        if(vehiclesPerBranchAndModel.size() == 0){
            log.info("-1");
            return null;
        }
        List<Vehicle> availableVehiclePerBranch = new ArrayList<>();

        vehiclesPerBranchAndModel.parallelStream().forEach(vehicle -> {
            List<Booking> activeBooking = bookingRepository.findByVehicleAndStartTimeAfterOrEndTimeBefore(vehicle, dateUtils.getDateFromHour(bookingRequest.getStartTime()), dateUtils.getDateFromHour(bookingRequest.getEndTime()));
            if(activeBooking != null && activeBooking.size()==0){
                availableVehiclePerBranch.add(vehicle);
            }
        });

        Optional<Vehicle> optionalVehicle = availableVehiclePerBranch.stream().min((vehicle1, vehicle2) -> vehicle1.getPrice().compareTo(vehicle2.getPrice()));
        if(optionalVehicle.isPresent()) {
            Booking booking = Booking.builder()
                    .vehicle(optionalVehicle.get())
                    .startTime(dateUtils.getDateFromHour(bookingRequest.getStartTime()))
                    .endTime(dateUtils.getDateFromHour(bookingRequest.getEndTime()))
                    .bookedAt(dateUtils.getCurrentTime())
                    .estimatedPrice(dateUtils.getHourDifference(dateUtils.getDateFromHour(bookingRequest.getStartTime()), dateUtils.getDateFromHour(bookingRequest.getEndTime())) * optionalVehicle.get().getPrice())
                    .paymentType(PaymentType.NETBANKING)
                    .transactionNumber("XXXX")
                    .bookingType(BookingType.PAY_PER_HOUR)
                    .build();
            log.info(String.valueOf(dateUtils.getHourDifference(dateUtils.getDateFromHour(bookingRequest.getStartTime()), dateUtils.getDateFromHour(bookingRequest.getEndTime())) * getDynamicPrice(optionalVehicle.get(), bookingRequest)));
            bookingRepository.save(booking);
            return booking;
        }

        return null;
    }
}
