/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.util;

import Sensors.Interfaces.City;
import Sensors.Interfaces.ITempreture;
import Sensors.core.Tempreture;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author khaled helal
 */
public class AvgTempComparator implements Comparator<City>{

    private Map<City, ITempreture> map;
    
    public AvgTempComparator(Map<City, ITempreture> map)
    {
        this.map = map;
    }
            
    @Override
    public int compare(City t, City t1) {
        return map.get(t).compareTo(map.get(t1));
    }
    
}
