package com.example.countriesapp.Models;

import com.example.countriesapp.MainActivity;

import java.util.Comparator;

public class CountryComperator implements Comparator<Country> {
    private int sortBy;
    boolean desc;
    public CountryComperator (int sortBy,boolean desc){
        this.sortBy=sortBy;
        this.desc=desc;
    }
    @Override
    public int compare(Country o1, Country o2) {
        if(sortBy== MainActivity.SORT_BY_NAME_CODE){
            if (!desc){
                return o1.getEnglishName().compareTo(o2.getEnglishName());
            }
            return o2.getEnglishName().compareTo(o1.getEnglishName());
        }
        if(sortBy==MainActivity.SORT_BY_AREA_CODE){
            if(!desc){
                if(o1.getArea()>o2.getArea())
                    return -1;
                if(o1.getArea()<o2.getArea())
                    return 1;
                return 0;
            }
            if(o1.getArea()>o2.getArea())
                return 1;
            if(o1.getArea()<o2.getArea())
                return -1;
            return 0;
        }
        return 0;
    }
}
