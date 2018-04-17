/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.Interfaces.Sensor;

/**
 *
 * @author khaled helal
 */
public abstract class Sensors implements Sensor{
    
    private double value;
    private String unit;

    public Sensors(double value, String unit) {
        setValue(value);
        setUnit(unit);
    }

    @Override
    public void setValue(double value) {
        this.value=value;
    }

    @Override
    public void setUnit(String unit) {
        this.unit=unit;
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
}
