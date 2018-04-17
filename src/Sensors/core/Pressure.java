/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

/**
 *
 * @author khaled helal
 */
public class Pressure extends Sensors{
    
    public Pressure(double value, String unit) {
        super(value, unit);
    }

    @Override
    public void setUnit(String unit) {
        if (unit.equalsIgnoreCase("mb")) {
            super.setUnit(unit);
        } else {
            throw new IllegalArgumentException("wrong unit");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
