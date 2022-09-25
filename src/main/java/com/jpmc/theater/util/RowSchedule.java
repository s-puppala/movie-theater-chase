package com.jpmc.theater.util;

public class RowSchedule {

	private int sequenceNum;
	private String startTime;
	private String title;
	private String runtime;
	private String price;
	
	public RowSchedule(int sequenceNum, String startTime, String title, String runtime, String price) {
		this.sequenceNum = sequenceNum;
		this.startTime = startTime;
		this.title = title;
		this.runtime = runtime;
		this.price = "$" + price;
	}

	public int getSequenceNum() {
		return sequenceNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getTitle() {
		return title;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getPrice() {
		return price;
	}
}
