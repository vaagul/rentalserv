package com.atlantis.rentalserv.utils;

import com.atlantis.rentalserv.service.booking.BookingServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({DateUtils.class})
public class DateUtilsTest {

    @Autowired
    DateUtils dateUtils;

    @Test
    public void checkDateFormatGivenHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateUtils.getDateFromHour(1);
        assertEquals(date.getHours(), 1);
        System.out.println(formatter.format(date));
    }

    @Test
    public void checkDateFormatOutOfBoundsHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateUtils.getDateFromHour(45);
        // Moved to the next day. Since the sample is contained within a day, it will remain as an anomaly
        assertEquals(date.getHours(), 21);
        System.out.println(formatter.format(date));
    }

    @Test
    public void checkCurrentTime() {
        Date date = dateUtils.getCurrentTime();
        assertTrue(date.getTime() - new Date().getTime() < 1000);
        // 1000 is worstcase time ms
    }

    @Test
    public void checkHourDifference() {
        Double hourDifference = dateUtils.getHourDifference(dateUtils.getDateFromHour(10), dateUtils.getDateFromHour(23));
        assertEquals(hourDifference, 13);
        // 1000 is worstcase time ms
    }
}
