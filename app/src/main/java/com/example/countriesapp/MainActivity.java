package com.example.countriesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.countriesapp.AsyncTasks.GetCountries;
import com.example.countriesapp.ListAdapters.CountriesListAdapter;
import com.example.countriesapp.Models.Country;
import com.example.countriesapp.Models.CountryComperator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button sortButton;
    ListView countriesListView;
    ArrayList<Country> countries;
    public static final int SORT_BY_NAME_CODE = 1;
    public static final int SORT_BY_AREA_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setClickListeners();
        AsyncTaskCallBack asyncTaskCallBack=new AsyncTaskCallBack() {
            @Override
            public void callBack(Object o) {
                ArrayList<Country> countries= (ArrayList<Country>) o;
                setCountriesListAdapter(countries);
            }
        };
        new GetCountries(asyncTaskCallBack,this).execute();
    }
    private void setUI(){
        sortButton=findViewById(R.id.sortButton);
        countriesListView=findViewById(R.id.countriesListView);
    }
    private void setCountriesListAdapter(ArrayList<Country> countries){
        if(countriesListView==null || countries==null || countries.size()==0)
            return;
        CountriesListAdapter countriesListAdapter=new CountriesListAdapter(this,countries);
        countriesListView.setAdapter(countriesListAdapter);
        this.countries=countries;

    }
    private Country getCountryByCode(String code){
        for(int i=0;i<countries.size();i++){
            if(countries.get(i).getAlpha3Code().equals(code))
                return countries.get(i);
        }
        return null;
    }
    private void setClickListeners(){
        countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country=countries.get(position);
                if(country.getBorderCountries()==null) {
                    ArrayList<String> borderCountriesCodes = country.getBorderCountriesCodes();
                    ArrayList<Country> borderCountries = new ArrayList<>();
                    for (int i = 0; i < borderCountriesCodes.size(); i++) {
                        Country borderCountry = getCountryByCode(borderCountriesCodes.get(i));
                        if (borderCountry != null)
                            borderCountries.add(borderCountry);
                    }
                    country.setBorderCountries(borderCountries);
                }
                Intent intent=new Intent(MainActivity.this,CountryBordersActivity.class);
                intent.putExtra("country", (Serializable) country);
                startActivity(intent);

            }
        });
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.sort_dialog_layout);
                dialog.show();
                Button closeDialogButton=dialog.findViewById(R.id.closeDialogButton);
                closeDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button sortByNameButton=dialog.findViewById(R.id.sortAscendingButton);
                Button sortByAreaButton=dialog.findViewById(R.id.sortDescendingButton);
                sortByNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSortTypeDialog(SORT_BY_NAME_CODE);
                        dialog.dismiss();
                    }
                });
                sortByAreaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSortTypeDialog(SORT_BY_AREA_CODE);
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void showSortTypeDialog(final int sortBy){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.sort_type_dialog_layout);
        dialog.show();
        Button closeDialogButton=dialog.findViewById(R.id.closeDialogButton);
        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button sortAscendingButton=dialog.findViewById(R.id.sortAscendingButton);
        Button sortDescendingButton=dialog.findViewById(R.id.sortDescendingButton);
        sortAscendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(countries,new CountryComperator(sortBy,false));
                setCountriesListAdapter(countries);
                dialog.dismiss();
            }
        });
        sortDescendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(countries,new CountryComperator(sortBy,true));
                setCountriesListAdapter(countries);
                dialog.dismiss();
            }
        });
    }


}
