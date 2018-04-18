/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.util.AvgTempComparator;
import Sensors.util.Date;
import Sensors.Interfaces.City;
import Sensors.Interfaces.DataAnalytics;
import Sensors.Interfaces.IHumidity;
import Sensors.Interfaces.IPressure;
import Sensors.Interfaces.ITempreture;
import Sensors.Interfaces.Measurments;
import Sensors.Interfaces.Sensor;
import Sensors.util.Filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author khaled helal
 */
public class DataAnalyticsImpl implements DataAnalytics {

    private ArrayList<Measurments> measurments;
    private static DataAnalyticsImpl instance = new DataAnalyticsImpl();
    private Filter filter = new Filter();
    
    private DataAnalyticsImpl() {
        measurments = new ArrayList<>();
    }    
    public static DataAnalyticsImpl getInstance()
    {
        return instance;
    }
    
    @Override
    public Map<City, Sensor> hottestTemperature(Date d1, Date d2) {

        ArrayList<City> cities = filter.citiyNames();

        Map<City, Sensor> maxTempMap = new HashMap<>();

        for (int i = 0; i < cities.size(); i++) {
            ArrayList<Measurments> cityMeasurmentses = filter.CityMeasurmentses(cities.get(i).getName(), d1, d2);
            Measurments maxTemp = Collections.max(cityMeasurmentses);
            maxTempMap.put(cities.get(i), maxTemp.getSensor().get("Tempreture"));
        }
        //System.out.println(maxTempMap);
        return maxTempMap;
    }

    @Override
    public Measurments averageMeasurements(City city, Date d1, Date d2) {

        ArrayList<Measurments> cityMeasurments = filter.CityMeasurmentses(city.getName(), d1, d2);
        
        Measurments m = new MeasurmentsImpl();

        double sumTemp = 0.0;
        for (int i = 0; i < cityMeasurments.size(); i++) {
            sumTemp += cityMeasurments.get(i).getSensor().get("Tempreture").getValue();
        }
        double sumHum = 0.0;
        for (int i = 0; i < cityMeasurments.size(); i++) {
            sumHum += cityMeasurments.get(i).getSensor().get("Humidity").getValue();
        }
        double sumPre = 0.0;
        for (int i = 0; i < cityMeasurments.size(); i++) {
            sumPre += cityMeasurments.get(i).getSensor().get("Pressure").getValue();
        }
        double avgTemp = (sumTemp / cityMeasurments.size());
        double avgHum = (sumHum / cityMeasurments.size());
        double avgPre = (sumPre / cityMeasurments.size());
        //System.out.println(avgData);
        m.setCity(city);
        ITempreture temp = new Tempreture();
        temp.setValue(avgTemp);
        m.getSensor().put("Tempreture", temp);
        IHumidity hum = new Humidity();
        hum.setValue(avgHum);
        m.getSensor().put("Humidity", hum);
        IPressure pre = new Pressure();
        pre.setValue(avgPre);
        m.getSensor().put("Pressure", hum);
        return m;
    }

    @Override
    public Set<City> citiesByTemperature(Date d1, Date d2) {

        ArrayList<City> cities = filter.citiyNames();
        Map<City, ITempreture> avgTempMap = new HashMap<>();

        for (int i = 0; i < cities.size(); i++) {
            double sumTemp = 0.0;
            ArrayList<Measurments> givenCity = filter.CityMeasurmentses(cities.get(i).getName(), d1, d2);
            for (int j = 0; j < givenCity.size(); j++) {
                double temp = givenCity.get(j).getSensor().get("Tempreture").getValue();
                sumTemp += temp;
            }
            double avg = sumTemp / givenCity.size();
            ITempreture avgtemp = new Tempreture(avg, "c");
            avgTempMap.put(givenCity.get(i).getCity(), avgtemp);
        }
        //System.out.println(avgTempMap);
        //sorting cities by avg and transfer the keys to a set
        TreeMap<City, ITempreture> sorted = new TreeMap<>(new AvgTempComparator(avgTempMap));
        sorted.putAll(avgTempMap);
        //System.out.println(sorted);
        Set<City> sortedCities = sorted.keySet();
        //System.out.println(sortedCities);
        return sortedCities;
    }

    @Override
    public ArrayList<Integer> alert(City city, Date d1, Date d2) {

        ArrayList<Measurments> cityMeasurments = filter.CityMeasurmentses(city.getName(), d1, d2);

        int dCounter = 0;
        int tCounter = 0;
        int pCounter = 0;
        int hCounter = 0;

        for (int i = 0; i < cityMeasurments.size(); i++) {
            double temp = cityMeasurments.get(i).getSensor().get("Tempreture").getValue();
            if (temp > 45) {
                tCounter++;
            }
        }

        for (int i = 0; i < cityMeasurments.size(); i++) {
            double hum = cityMeasurments.get(i).getSensor().get("Humidity").getValue();
            if (hum > 35) {
                hCounter++;
            }
        }

        for (int i = 0; i < cityMeasurments.size(); i++) {
            double pre = cityMeasurments.get(i).getSensor().get("Pressure").getValue();
            if (pre > 2050 || pre < 1010) {
                pCounter++;
            }
        }

        for (int i = 0; i < cityMeasurments.size(); i++) {
            double d = cityMeasurments.get(i).getSensor().get("Distance").getValue();
            if (d < 21) {
                dCounter++;
            }
        }

        ArrayList<Integer> alerts = new ArrayList<>();
        alerts.add(dCounter);
        alerts.add(tCounter);
        alerts.add(pCounter);
        alerts.add(hCounter);
        //System.out.println(alerts);

        return alerts;
    }
}
