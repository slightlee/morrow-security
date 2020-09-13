package com.morrow.common.util;

import java.time.*;
import java.util.Date;

/**
 * TODO
 *
 * @Author Tomorrow
 * @Date 2020/9/7 12:46 下午
 */
public class DateUtil {

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }



    private final static int EXPIRE = 3600 * 8;

    public static void main(String[] args) {



//        LocalDateTime nowTime = LocalDateTime.now();
//        //过期时间
//        LocalDateTime expireTime = new Date(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() + EXPIRE * 1000).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
//
//
//        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println(nowTime);
//        System.out.println(expireTime);

        Date now = new Date();
        LocalDateTime lonow = LocalDateTime.now();

        Date ldate = DateUtil.asDate(LocalDateTime.now());
        System.out.println(now);
        System.out.println(ldate);

        Date now1 = new Date();
        LocalDateTime localDateTime = DateUtil.asLocalDateTime(now1);
        System.out.println(localDateTime);
        System.out.println(lonow);
    }
}
