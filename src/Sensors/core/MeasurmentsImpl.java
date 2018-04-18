/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.util.Date;
import Sensors.Interfaces.City;
import Sensors.Interfaces.Measurments;
import Sensors.Interfaces.Sensor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author khaled helal
 */
public class MeasurmentsImpl implements Measurments{

    private City city;
    // map String ensor
    private Map<String, Sensor> sensors = new HashMap<>();
    private Date date;

    public MeasurmentsImpl(City city, Map<String, Sensor> sensors, Date date) {
        setCity(city);
        setSensor(sensors);
        setDate(date);
    }

    public MeasurmentsImpl() {
    }
    
    @Override
    public void setCity(City city) {
        this.city=city;
    }

    @Override
    public void setSensor(Map<String, Sensor> sensors) {
        this.sensors=sensors;
    }

    @Override
    public void setDate(Date date) {
        this.date=date;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public Map<String, Sensor> getSensor() {
        return sensors;
    }

    public Sensor getSensor(String name) {
        return sensors.get(name);
    }
    
    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", 
                city.getName(),city.getGps(),sensors, date);
    }

    @Override
    public int compareTo(Measurments t) {
        if(this.sensors.get("Tempreture").getValue()> t.getSensor().get("Tempreture").getValue())
            return 1;
        else if(this.sensors.get("Tempreture").getValue()< t.getSensor().get("Tempreture").getValue())
            return -1;
        else
            return 0;
    }

    
}
