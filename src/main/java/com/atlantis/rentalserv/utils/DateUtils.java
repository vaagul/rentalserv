package com.atlantis.rentalserv.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DateUtils {

    public Date getDateFromHour(int hour){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    public Date getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return date;
    }

    public Double getHourDifference(Date startDate, Date endDate){
        long diff = endDate.getTime() - startDate.getTime();
        TimeUnit time = TimeUnit.HOURS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        return Double.parseDouble(difference + "");
    }
}
