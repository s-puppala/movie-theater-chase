package com.jpmc.theater.util;

import java.util.List;

public class JsonSchedule {

	private String date;
	private List<RowSchedule> showingsToday;
	
	public JsonSchedule(String date, List<RowSchedule> showingsToday) {
		super();
		this.date = date;
		this.showingsToday = showingsToday;
	}

	public String getDate() {
		return date;
	}

	public List<RowSchedule> getShowingsToday() {
		return showingsToday;
	}
}
