/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.Interfaces;

/**
 *
 * @author khaled helal
 */
public interface City extends Comparable<City>{
    public abstract void setName(String name);
    public abstract void SetGps(GPS gps);
    public abstract String getName();
    public abstract GPS getGps();
}
