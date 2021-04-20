package by.exadel.internship.comparator;

import by.exadel.internship.entity.location.City;

import java.util.Comparator;

public class CityComparator implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
       return o1.getName().compareTo(o2.getName());
    }
}
