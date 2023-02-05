package com.commerce.song.util;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String FORMAT_DAY_DASH = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_DAY_NON_DASH = "yyyyMMdd hhmmss";
    public static final String FORMAT_DATE_DASH = "yyyy-MM-dd";
    public static final String FORMAT_DATE_NON_DASH = "yyyyMMdd";
    public static final String FORMAT_TIME_DASH = "hh:mm:ss";
    public static final String FORMAT_TIME_NON_DASH = "hhmmss";

    public static String getNow(String format) {
        if(!StringUtils.hasText(format)) format = FORMAT_DAY_DASH;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTImeFormatter = DateTimeFormatter.ofPattern(format);
        return now.format(dateTImeFormatter);
    }
}
