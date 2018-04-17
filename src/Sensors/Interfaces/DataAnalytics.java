/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.Interfaces;

import Sensors.util.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author khaled helal
 */
public interface DataAnalytics {

    public abstract Map<String, Double> hottestTemperature(Date d1, Date d2);

    public abstract ArrayList<Double> averageMeasurements(City city, Date d1, Date d2);

    public abstract Set<City> citiesByTemperature(Date d1, Date d2);

    public abstract ArrayList<Integer> alert(City city, Date d1, Date d2);
}
