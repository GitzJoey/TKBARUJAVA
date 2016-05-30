package com.tkbaru.model.chart;

import java.util.HashMap;
import java.util.List;

public class FlotBarChart {
	private String label = "bar";
	private List<List<Long[]>> data;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<List<Long[]>> getData() {
		return data;
	}
	public void setData(List<List<Long[]>> data) {
		this.data = data;
	}
	
}
