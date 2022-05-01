package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.enums.BookingType;
import com.atlantis.rentalserv.enums.DriverStatus;
import com.atlantis.rentalserv.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    Long id;

    @ManyToOne(optional = false)
    Vehicle vehicle;

    @DateTimeFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")
    Date startTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")
    Date endTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")
    Date bookedAt;

    @Column(nullable = false)
    Double estimatedPrice;

    @Column
    Double deductionPrice;

    @Enumerated(EnumType.STRING)
    PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    DriverStatus driverStatus;

    @Column
    String transactionNumber;

    @Column
    Long estimatedJourneyDistance;

    @Enumerated(EnumType.STRING)
    BookingType bookingType;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public String getVehicle() {
        return vehicle.getVehicleName();
    }

}
