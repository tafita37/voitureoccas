package com.dev.model.converter;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

public class DateToTimestampConverter implements Converter<Date, Timestamp> {

    @Override
    public Timestamp convert(Date source) {
        return (source != null) ? new Timestamp(source.getTime()) : null;
    }
}
