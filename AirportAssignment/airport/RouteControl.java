package com.airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class RouteControl {
	public List<Airline> getMostFreqAirlines(List<Route> routes){
		Map<Airline,Set<City>> airlineToCity=new HashMap<Airline, Set<City>>();
		for(Route route:routes) {
			if(!airlineToCity.containsKey(route.getAirLine())) {
				airlineToCity.put(route.getAirLine(), new HashSet<City>());
			}
			airlineToCity.get(route.getAirLine()).add(route.getSource());
			airlineToCity.get(route.getAirLine()).add(route.getDestination());
		}
		PriorityQueue<Entry<Airline, Set<City>>> pq=new PriorityQueue<Map.Entry<Airline,Set<City>>>((a,b)->a.getValue().size()-b.getValue().size());
		List<Airline> res=new ArrayList<Airline>();
		for(Entry<Airline, Set<City>> en:airlineToCity.entrySet()) {
			pq.add(en);
			if(pq.size()>3)
				pq.poll();
		}
		while(!pq.isEmpty()) {
			Entry<Airline, Set<City>> en=pq.poll();
			System.out.println(en.getKey().getAirlineName()+" : "+en.getValue().size());
			res.add(en.getKey());
		}
		Collections.reverse(res);
		return res;
	}
	
	
	public List<Airline> getMostAirLinesWithDirectRoute(List<Route> routes){
		Map<Airline,Integer> airlineToNum=new HashMap<Airline, Integer>();
		Set<Route> routeSet=new HashSet<Route>();
		for(Route route:routes) {
			if(route.getStops()!=0)
				continue;
			if(!routeSet.contains(route)) {
				routeSet.add(route);
				airlineToNum.put(route.getAirLine(), airlineToNum.getOrDefault(route.getAirLine(), 0)+1);
			}
		}
		PriorityQueue<Entry<Airline,Integer>> pq=new PriorityQueue<Map.Entry<Airline,Integer>>((a,b)->a.getValue()-b.getValue());
		List<Airline> res=new ArrayList<Airline>();
		for(Entry<Airline, Integer> en:airlineToNum.entrySet()) {
			pq.add(en);
			if(pq.size()>3)
				pq.poll();
		}
		while(!pq.isEmpty()) {
			Entry<Airline, Integer> en=pq.poll();
			System.out.println(en.getKey().getAirlineName()+" : "+en.getValue());
			res.add(en.getKey());
		}
		Collections.reverse(res);
		return res;
	}
	
	public List<City> getMostConnectedCity(List<Route> routes){
		Map<City,Integer> cityToNum=new HashMap<City, Integer>();
		Set<Route> routeSet=new HashSet<Route>();
		for(Route route:routes) {
			if(!routeSet.contains(route)) {
				routeSet.add(route);
				cityToNum.put(route.getSource(), cityToNum.getOrDefault(route.getSource(),0)+1);
				cityToNum.put(route.getDestination(), cityToNum.getOrDefault(route.getDestination(),0)+1);
			}
		}
		PriorityQueue<Entry<City, Integer>> pq=new PriorityQueue<Map.Entry<City,Integer>>((a,b)->a.getValue()-b.getValue());
		List<City> res=new ArrayList<City>();
		for(Entry<City, Integer> en:cityToNum.entrySet()) {
			pq.add(en);
			if(pq.size()>10)
				pq.poll();
		}
		while(!pq.isEmpty()) {
			Entry<City, Integer> en=pq.poll();
			System.out.println(en.getKey()+" : "+en.getValue());
			res.add(en.getKey());
		}
		Collections.reverse(res);
		return res;
	}
}
