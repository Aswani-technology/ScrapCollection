package com.scrapcollection.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter {
	

	public static LocalDate convert(String date)
	{
		  date="2018-03-10";
		  DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate ld = LocalDate.parse(date, DATEFORMATTER);
		    
			return ld;
		     
	}

}
