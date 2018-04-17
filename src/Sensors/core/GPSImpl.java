/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.Interfaces.GPS;

/**
 *
 * @author khaled helal
 */
public class GPSImpl implements GPS{
    
    private double lat;
    private double lng;

    public GPSImpl(double lat, double lon) {
        setLatitude(lat);
        setLongitude(lon);
    }

    @Override
    public double getLatitude() {
        return lat;
    }

    @Override
    public double getLongitude() {
        return lng;
    }

    @Override
    public String toString() {
        return lat + ", " + lng;
    }

    @Override
    public void setLatitude(double lat) {
        this.lat = lat;
    }

    @Override
    public void setLongitude(double lon) {
        this.lng = lon;
    }
   
}
