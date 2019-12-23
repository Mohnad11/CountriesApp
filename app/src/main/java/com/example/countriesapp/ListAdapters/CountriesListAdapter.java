package com.example.countriesapp.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.countriesapp.Models.Country;
import com.example.countriesapp.R;

import java.util.List;

public class CountriesListAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country> countries;
    public CountriesListAdapter(@NonNull Context context, @NonNull List<Country> countries) {
        super(context, R.layout.country_list_item, countries);
        this.context=context;
        this.countries=countries;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.country_list_item, parent, false);
        Country country=countries.get(position);
        TextView englishNameTextView=view.findViewById(R.id.englishNameTextView);
        TextView nativeNameTextView=view.findViewById(R.id.nativeNameTextView);
        englishNameTextView.setText(country.getEnglishName());
        nativeNameTextView.setText(country.getNativeName());
        return view;
    }
}