/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.util;

import Sensors.Interfaces.Measurments;
import Sensors.Interfaces.Sensor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Sensors.core.CityImpl;
import Sensors.core.Distance;
import Sensors.core.GPSImpl;
import Sensors.core.Humidity;
import Sensors.core.MeasurmentsImpl;
import Sensors.core.Pressure;
import Sensors.core.Tempreture;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author khaled helal
 */
public class Filter 
{
    public static ArrayList<Measurments> readFromFile() 
    {
        ArrayList<Measurments> measurments = new ArrayList<>();
        try {
            Scanner In = new Scanner(new File("sensor-data.txt"));
            while (In.hasNextLine()) {
                String s = In.next();
                String[] linearray = s.split(";");
                String cityName = linearray[0];
                GPSImpl gps = new GPSImpl(Double.parseDouble(linearray[1]), Double.parseDouble(linearray[2]));
                CityImpl city = new CityImpl(cityName, gps);

                Map<String, Sensor> sensor = new HashMap<>();
                sensor.put("Tempreture", new Tempreture(Double.parseDouble(linearray[3]), linearray[4]));
                sensor.put("Humidity", new Humidity(Double.parseDouble(linearray[5]), linearray[6]));
                sensor.put("Pressure", new Pressure(Double.parseDouble(linearray[7]), linearray[8]));
                sensor.put("Distance", new Distance(Double.parseDouble(linearray[9]), linearray[10]));

                String strDate = linearray[11];
                String[] dateArray = strDate.split("/");
                Date date = new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
                measurments.add(new MeasurmentsImpl(city, sensor, date));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found " + ex);
        }
        return measurments;
    }
    
    //return all cities in the file
    public static ArrayList<String> citiyNames()
    {
        ArrayList<Measurments> measurements = readFromFile();
        ArrayList<String> names = new ArrayList<>();
        
        for (int i = 0; i < measurements.size(); i++) 
        {
            if(!names.contains(measurements.get(i).getCity().getName()))
                names.add(measurements.get(i).getCity().getName());
        }
        
        return names;
    }
    
    //return given city between two dates
    public static ArrayList<Measurments> Cities(String cityName, Date d1, Date d2) 
    {
        ArrayList<Measurments> measurements = readFromFile();
        ArrayList<Measurments> cities = new ArrayList<>();
        if (d2.compareTo(d1) > 0) {
            Date temp = d2;
            d2 = d1;
            d1 = temp;
        }

        for (int i = 0; i < measurements.size(); i++) {
            if (measurements.get(i).getDate().compareTo(d2) > -1 && measurements.get(i).getDate().compareTo(d1) < 0) {
                if (measurements.get(i).getCity().getName().equals(cityName)) {
                    cities.add(measurements.get(i));
                }
            }
        }
        return cities;
    }

    // return all cities between two dates
    public static ArrayList<Measurments> Cities(Date d1, Date d2) {
        ArrayList<Measurments> measurements = readFromFile();
        ArrayList<Measurments> cities = new ArrayList<>();
        if (d2.compareTo(d1) > 0) {
            Date temp = d2;
            d2 = d1;
            d1 = temp;
        }

        for (int i = 0; i < measurements.size(); i++) {
            if (measurements.get(i).getDate().compareTo(d2) > -1 && measurements.get(i).getDate().compareTo(d1) < 0) {
                cities.add(measurements.get(i));
            }
        }
        return cities;
    }
}
