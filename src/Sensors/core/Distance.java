/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.Interfaces.IDistance;
import Sensors.Interfaces.Sensor;

/**
 *
 * @author khaled helal
 */
public class Distance implements IDistance{
    
    private double value;
    private String unit;
    
    public Distance(double value, String unit) {
        setValue(value);
        setUnit(unit);
    }

    public Distance() {
    }
    
    @Override
    public void setUnit(String unit) {
        if (unit.equalsIgnoreCase("m")) {
            this.unit=unit;
        } else {
            throw new IllegalArgumentException("wrong unit");
        }
    }

    @Override
    public void setValue(double value) {
        this.value=value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return unit;
    }
    
    @Override
    public String toString() {
        return value + " " + unit;
    }
    
    @Override
    public int compareTo(Sensor t) {
        if(this.getValue()>t.getValue())
            return 1;
        else if(this.getValue()<t.getValue())
            return -1;
        return 0;
    }
}
