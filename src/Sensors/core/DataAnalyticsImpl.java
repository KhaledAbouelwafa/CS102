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
import Sensors.Interfaces.Measurments;
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

    @Override
    public Map<String, Double> hottestTemperature(Date d1, Date d2) {

        ArrayList<String> cityName = Filter.citiyNames();

        Map<String, Double> maxTempMap = new HashMap<>();

        for (int i = 0; i < cityName.size(); i++) {
            ArrayList<Measurments> city = Filter.Cities(cityName.get(i), d1, d2);
            Measurments max = Collections.max(city);
            maxTempMap.put(cityName.get(i), max.getSensor().get("Tempreture").getValue());
        }
        //System.out.println(maxTempMap);
        return maxTempMap;
    }

    @Override
    public ArrayList<Double> averageMeasurements(City city, Date d1, Date d2) {

        ArrayList<Measurments> givenCity = Filter.Cities(city.getName(), d1, d2);

        ArrayList<Double> avgData = new ArrayList<>();

        double sumTemp = 0.0;
        for (int i = 0; i < givenCity.size(); i++) {
            sumTemp += givenCity.get(i).getSensor().get("Tempreture").getValue();
        }
        double sumHum = 0.0;
        for (int i = 0; i < givenCity.size(); i++) {
            sumHum += givenCity.get(i).getSensor().get("Humidity").getValue();
        }
        double sumPre = 0.0;
        for (int i = 0; i < givenCity.size(); i++) {
            sumPre += givenCity.get(i).getSensor().get("Pressure").getValue();
        }
        avgData.add(sumTemp / givenCity.size());
        avgData.add(sumHum / givenCity.size());
        avgData.add(sumPre / givenCity.size());
        //System.out.println(avgData);
        return avgData;
    }

    @Override
    public Set<City> citiesByTemperature(Date d1, Date d2) {

        ArrayList<String> cityName = Filter.citiyNames();
        Map<City, Double> avgTempMap = new HashMap<>();

        for (int i = 0; i < cityName.size(); i++) {
            double sumTemp = 0.0;
            ArrayList<Measurments> givenCity = Filter.Cities(cityName.get(i), d1, d2);
            for (int j = 0; j < givenCity.size(); j++) {
                double temp = givenCity.get(j).getSensor().get("Tempreture").getValue();
                sumTemp += temp;
            }
            double avg = sumTemp / givenCity.size();
            avgTempMap.put(givenCity.get(i).getCity(), avg);
        }
        //System.out.println(avgTempMap);
        //sorting cities by avg and transfer the keys to a set
        TreeMap<City, Double> sorted = new TreeMap<>(new AvgTempComparator(avgTempMap));
        sorted.putAll(avgTempMap);
        //System.out.println(sorted);
        Set<City> sortedCities = sorted.keySet();
        //System.out.println(sortedCities);
        return sortedCities;
    }

    @Override
    public ArrayList<Integer> alert(City city, Date d1, Date d2) {

        ArrayList<Measurments> givenCity = Filter.Cities(city.getName(), d1, d2);

        int dCounter = 0;
        int tCounter = 0;
        int pCounter = 0;
        int hCounter = 0;

        for (int i = 0; i < givenCity.size(); i++) {
            double temp = givenCity.get(i).getSensor().get("Tempreture").getValue();
            if (temp > 45) {
                tCounter++;
            }
        }

        for (int i = 0; i < givenCity.size(); i++) {
            double hum = givenCity.get(i).getSensor().get("Humidity").getValue();
            if (hum > 35) {
                hCounter++;
            }
        }

        for (int i = 0; i < givenCity.size(); i++) {
            double pre = givenCity.get(i).getSensor().get("Pressure").getValue();
            if (pre > 2050 || pre < 1010) {
                pCounter++;
            }
        }

        for (int i = 0; i < givenCity.size(); i++) {
            double d = givenCity.get(i).getSensor().get("Distance").getValue();
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
