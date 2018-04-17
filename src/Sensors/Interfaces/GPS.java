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
public interface GPS {
    public abstract void setLatitude(double lat);
    public abstract void setLongitude(double lon);
    public abstract double getLatitude();
    public abstract double getLongitude();
}
