package com.airport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class RouteFactory {
	public List<Route> createListRoute(){
		List<Route> routes=new ArrayList<Route>();
		Route route;
		try {
			String[] lines=Files.readAllLines(new File("resource/airline-routes.dat").toPath()).toArray(new String[0]);
			for(String line:lines) {
				String[] parts=line.split(",");
				if(parts.length<9)
					continue;
				boolean isValid=true;
				Airline airline=null;
				City source=null;
				City destination=null;
				//2I,8359,LIM,2789,CUZ,2812,,0,142 141
				for(int i=0;i<6;i++) {
					if(parts[i].equals("\\N")) {
						isValid=false;
					}
					if(!isValid)
						break;
					if(i==1) {
						airline=new Airline(parts[0], parts[1]);
					}
					else if(i==3){
						source=new City(parts[2], parts[3]);
					}
					else if(i==5){
						destination=new City(parts[4], parts[5]);
					}
					
				}
				if(!isValid) {
					//System.out.println("Invalid data");
					continue;
				}
				boolean codeShare=(parts[6]=="Y"?true:false);
				int stops=Integer.parseInt(parts[7]);
				String equipments=parts[8];
				route=new Route(airline, source, destination, stops, equipments, codeShare);
				routes.add(route);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return routes;
	}
}
