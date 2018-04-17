/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.Interfaces;

import Sensors.util.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author khaled helal
 */
public interface Measurments extends Comparable<Measurments>{
    public abstract void setCity(City city);
    public abstract void setSensor(Map<String, Sensor> sensors);
    public abstract void setDate(Date date);
    public abstract City getCity();
    public abstract Map<String, Sensor> getSensor();
    public abstract Date getDate();
}
