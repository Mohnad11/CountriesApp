package com.example.countriesapp.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Country implements Serializable {
    private String nativeName;
    private String englishName;
    private String alpha3Code;
    private double area;
    private ArrayList<Country> borderCountries;
    private ArrayList<String> borderCountriesCodes;
    public Country(){

    }
    public Country(String englishName, String nativeName,String alpha3Code,double area, ArrayList<String>  borderCountriesCodes) {
        this.nativeName = nativeName;
        this.englishName = englishName;
        this.alpha3Code=alpha3Code;
        this.area=area;
        this.borderCountriesCodes = borderCountriesCodes;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public ArrayList<Country> getBorderCountries() {
        return borderCountries;
    }

    public void setBorderCountries(ArrayList<Country> borderCountries) {
        this.borderCountries = borderCountries;
    }

    public ArrayList<String> getBorderCountriesCodes() {
        return borderCountriesCodes;
    }

    public void setBorderCountriesCodes(ArrayList<String> borderCountriesCodes) {
        this.borderCountriesCodes = borderCountriesCodes;
    }
}
