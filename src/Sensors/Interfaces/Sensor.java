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
public interface Sensor {
    public abstract void setValue(double value);
    public abstract void setUnit(String unit);
    public abstract double getValue();
    public abstract String getUnit();
}
