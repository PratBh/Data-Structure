package com.airport;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		RouteFactory factory=new RouteFactory();
		List<Route> routes=factory.createListRoute();
		System.out.println(routes.size());
		RouteControl rc=new RouteControl();
		List<Airline> als=rc.getMostFreqAirlines(routes);
		als.stream().forEach(System.out::println);
		List<Airline> mostDirects=rc.getMostAirLinesWithDirectRoute(routes);
		mostDirects.stream().forEach(System.out::println);
		List<City> cities=rc.getMostConnectedCity(routes);
		cities.stream().forEach(System.out::println);
	}

}
