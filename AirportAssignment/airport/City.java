package com.airport;

public class City {
	private String airportName;
	private String airportCode;
	
	
	public City(String airportName, String airportCode) {
		this.airportName = airportName;
		this.airportCode = airportCode;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	@Override
	public int hashCode() {
		int id=Integer.parseInt(this.getAirportCode());
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		if (airportName == null) {
			if (other.airportName != null)
				return false;
		} else if (!airportName.equals(other.airportName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "City :" + airportName ;
	}
	
	
	
}
