package com.item1024.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换器
 * @author xiongyu
 * @version Create at ：2017/5/20 20:41
 */

public class StringToDateConverter implements Converter<String, Date> {

    private String datePattern;

    public StringToDateConverter(String datePattern) {
        this.datePattern = datePattern;
    }

    public Date convert(String s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            dateFormat.setLenient(false);
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date format. Please use this pattern\"" + datePattern + "\"");
        }
    }
}

