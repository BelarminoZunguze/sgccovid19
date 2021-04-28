package uem.mz.sgccovid19.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class conversaoDeData {
	
	@SuppressWarnings("unused")
	private String converteDataParaString(Date data){
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		return sdt.format(data);
	}
	
	@SuppressWarnings("unused")
	private Date converteStringParaData(String data) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return (Date)sdf.parse(data);		
	}
	
	private Calendar converterDateParaCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	//Convert Calendar to Date
	@SuppressWarnings("unused")
	private Date converterCalendarParaDate(Calendar calendar) {
		return calendar.getTime();
	}
 
	public static void main (String args[]) throws ParseException{
		conversaoDeData cd = new conversaoDeData();
		//System.out.println(cd.converteDataParaString(new Date()));
		//System.out.println(cd.converteStringParaData("31/12/2019"));
		cd.converterDateParaCalendar(new Date());		
	}
	
}
