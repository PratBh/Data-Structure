package Immutable;

import java.util.HashMap;
import java.util.Map;

public final class Student {
	private final String name;
	private final int regNo;
	private final Map<String,String> metaData;
	public Student(String name, int regNo, Map<String, String> metaData) {
		super();
		this.name = name;
		this.regNo = regNo;
		Map<String, String> tempDate=new HashMap<String, String>();
		for(Map.Entry<String, String> entry:metaData.entrySet()) {
			tempDate.put(entry.getKey(),entry.getValue());
		}
		this.metaData=tempDate;
	}
	public String getName() {
		return name;
	}
	public int getRegNo() {
		return regNo;
	}
	public Map<String, String> getMetaData() {
		Map<String, String> tempDate=new HashMap<String, String>();
		for(Map.Entry<String, String> entry:metaData.entrySet()) {
			tempDate.put(entry.getKey(),entry.getValue());
		}
		return tempDate;
	}
	
}
