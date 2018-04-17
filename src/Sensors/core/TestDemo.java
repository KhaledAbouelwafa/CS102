/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors.core;

import Sensors.Interfaces.City;
import Sensors.Interfaces.GPS;
import Sensors.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author khaled helal
 */
public class TestDemo 
{
    public static void main(String[] args) {

        DataAnalyticsImpl f = new DataAnalyticsImpl();
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("------- Choose from the following methods ---------\n"
                    + "1. hottestTemperature(Date d1, Date d2)\n"
                    + "2. averageMeasurements(City city, Date d1, Date d2) \n"
                    + "3. citiesByTemperature(Date d1, Date d2)\n"
                    + "4. alert(City city, Date d1, Date d2) \n"
                    + "Enter 0 to end the program");
            int input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter dates as name/month/year");
                    System.out.println("Enter the 1st date");
                    int day1A = in.nextInt();
                    int month1A = in.nextInt();
                    int year1A = in.nextInt();
                    Date d1A = new Date(day1A, month1A, year1A);
                    System.out.println("Enter the 2nd date");
                    int day2A = in.nextInt();
                    int month2A = in.nextInt();
                    int year2A = in.nextInt();
                    Date d2A = new Date(day2A, month2A, year2A);
                    Map<String, Double> hottestMap = f.hottestTemperature(d1A, d2A);
                    Iterator<String> it1 = hottestMap.keySet().iterator();
                    System.out.printf("HOTTEST TEMPERATURE DURING %s AND %s\n", d1A, d2A);
                    while (it1.hasNext()) {
                        String name = it1.next();
                        Double max = hottestMap.get(name);
                        System.out.printf("%-10s->\t%.2f\n", name, max);
                    }
                    break;
                case 2:
                    System.out.println("Enter dates as name/month/year");
                    System.out.println("Enter the 1st date");
                    int day1B = in.nextInt();
                    int month1B = in.nextInt();
                    int year1B = in.nextInt();
                    Date d1B = new Date(day1B, month1B, year1B);
                    System.out.println("Enter the 2nd date");
                    int day2B = in.nextInt();
                    int month2B = in.nextInt();
                    int year2B = in.nextInt();
                    Date d2B = new Date(day2B, month2B, year2B);
                    System.out.println("Enter city name then cordinates");
                    String name1 = in.next();
                    double lat1 = in.nextDouble();
                    double lon1 = in.nextDouble();
                    GPS gps1 = new GPSImpl(lat1, lon1);
                    City city1 = new CityImpl(name1, gps1);
                    ArrayList<Double> avgM = f.averageMeasurements(city1, d1B, d2B);
                    System.out.printf("AVERAGE VALUES OF CITY %s DURING %s AND %s\n", name1, d1B, d2B);
                    System.out.printf("%s -> %.2f C -> %.2f %% -> %.2f mb\n",
                            name1, avgM.get(0), avgM.get(1), avgM.get(2));
                    break;
                case 3:
                    System.out.println("Enter dates as name/month/year");
                    System.out.println("Enter the 1st date");
                    int day1C = in.nextInt();
                    int month1C = in.nextInt();
                    int year1C = in.nextInt();
                    Date d1C = new Date(day1C, month1C, year1C);
                    System.out.println("Enter the 2nd date");
                    int day2C = in.nextInt();
                    int month2C = in.nextInt();
                    int year2C = in.nextInt();
                    Date d2C = new Date(day2C, month2C, year2C);
                    Set<City> cities = f.citiesByTemperature(d1C, d2C);
                    Iterator<City> it2 = cities.iterator();
                    System.out.printf("SET OF CITIES ORGANIZED BY THEIR INCREASING ORDER OF AVG TEMPERATURE\n");
                    String output = "";
                    while (it2.hasNext()) {
                        City city = it2.next();
                        String name = city.getName();

                        output += name + " -> ";
                    }
                    output = output.substring(0, output.lastIndexOf("->"));
                    System.out.println(output);
                    break;
                case 4:
                    System.out.println("Enter dates as name/month/year");
                    System.out.println("Enter the 1st date");
                    int day1D = in.nextInt();
                    int month1D = in.nextInt();
                    int year1D = in.nextInt();
                    Date d1D = new Date(day1D, month1D, year1D);
                    System.out.println("Enter the 2nd date");
                    int day2D = in.nextInt();
                    int month2D = in.nextInt();
                    int year2D = in.nextInt();
                    Date d2D = new Date(day2D, month2D, year2D);
                    System.out.println("Enter city name then cordinates");
                    String name2 = in.next();
                    double lat2 = in.nextDouble();
                    double lon2 = in.nextDouble();
                    GPS gps2 = new GPSImpl(lat2, lon2);
                    City city2 = new CityImpl(name2, gps2);
                    ArrayList<Integer> alerts = f.alert(city2, d1D, d2D);
                    System.out.printf("ALERTS OF CITY %s BETWEEN %s AND %s\n", name2, d1D, d2D);
                    System.out.printf("Distance Alert: %d\n"
                            + "Temperature Alert: %d\n"
                            + "Pressure Alert: %d\n"
                            + "Humidity Alert: %d",alerts.get(0),alerts.get(1),alerts.get(2),alerts.get(3));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Your Input is wrong. try again");
                    break;
            }

            System.out.println("\n*************************************\n");

        } while (true);
        /*
        Date d11 = new Date(1, 1, 2012);
        Date d22 = new Date(22, 6, 2017);
        GPSImpl gps = new GPSImpl(24.7136, 46.6753);
        CityImpl c = new CityImpl("Riyadh", gps);
        
        f.hottestTemperature(d11, d22);
        f.averageMeasurements(c, d11, d22);
        f.citiesByTemperature(d11, d22);
        f.alert(c, d11, d22);
         */
    }
}
