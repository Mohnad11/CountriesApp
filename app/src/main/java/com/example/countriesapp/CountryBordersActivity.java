package com.example.countriesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.countriesapp.ListAdapters.CountriesListAdapter;
import com.example.countriesapp.Models.Country;

import java.util.ArrayList;

public class CountryBordersActivity extends AppCompatActivity {
    Country country;
    TextView englishNameTextView;
    TextView nativeNameTextView;
    ListView countriesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_borders);
        setUI();
        Country country= (Country) getIntent().getSerializableExtra("country");
        if(country==null){
            this.finish();
            return;
        }
        this.country=country;
        englishNameTextView.setText(country.getEnglishName());
        nativeNameTextView.setText(country.getNativeName());
        setCountriesListAdapter(country.getBorderCountries());
    }
    private void setUI(){
        englishNameTextView=findViewById(R.id.englishNameTextView);
        nativeNameTextView=findViewById(R.id.nativeNameTextView);
        countriesListView=findViewById(R.id.countriesListView);
    }
    private void setCountriesListAdapter(ArrayList<Country> countries){
        if(countriesListView==null || countries==null || countries.size()==0)
            return;
        CountriesListAdapter countriesListAdapter=new CountriesListAdapter(this,countries);
        countriesListView.setAdapter(countriesListAdapter);
    }
}
