package com.airport;

public class Route {
	private Airline airLine;
	private City source;
	private City destination;
	private int stops;
	private String equipments;
	private boolean codeshare;
	public Route(Airline airLine, City source, City destination, int stops, String equipments, boolean codeshare) {
		super();
		this.airLine = airLine;
		this.source = source;
		this.destination = destination;
		this.stops = stops;
		this.equipments = equipments;
		this.codeshare = codeshare;
	}
	public Airline getAirLine() {
		return airLine;
	}
	public void setAirLine(Airline airLine) {
		this.airLine = airLine;
	}
	public City getSource() {
		return source;
	}
	public void setSource(City source) {
		this.source = source;
	}
	public City getDestination() {
		return destination;
	}
	public void setDestination(City destination) {
		this.destination = destination;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public String getEquipments() {
		return equipments;
	}
	public void setEquipments(String equipments) {
		this.equipments = equipments;
	}
	public boolean isCodeshare() {
		return codeshare;
	}
	public void setCodeshare(boolean codeshare) {
		this.codeshare = codeshare;
	}
	@Override
	public int hashCode() {
		int result=this.airLine.hashCode()+this.getSource().hashCode()+this.getDestination().hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Route))
			return false;
		if(obj==this)
			return true;
		Route r = (Route) obj;
		if(r.getAirLine().equals(this.getAirLine())) {
			if(r.getSource().equals(this.getSource())&&r.getDestination().equals(this.getDestination()))
			{
				if(r.getStops()==this.getStops())
					return true;
			}
		}
		return false;
	}
	
	
}
