package com.michal.kurek.project.healthclinic.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static Date convert(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.error("Error while parsing date {}", date, e);
            return null;
        }
    }

}
