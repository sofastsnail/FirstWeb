package com.snail.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			if (date != null && date != "") {
				return simpleDateFormat.parse(date);
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
