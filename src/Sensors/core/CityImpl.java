/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.Interfaces.City;
import Sensors.Interfaces.GPS;

/**
 *
 * @author khaled helal
 */
public class CityImpl implements City{

    private String name;
    private GPS gps;

    public CityImpl(String name, GPS gps) {
        setName(name);
        SetGps(gps);
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public GPS getGps() {
         return gps;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void SetGps(GPS gps) {
        this.gps=gps;
    }

    @Override
    public int compareTo(City t) {
        return this.name.compareToIgnoreCase(t.getName());
    }

    @Override
    public String toString() {
        return name + ", "+gps;
    }
    
}
