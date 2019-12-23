package com.example.countriesapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.countriesapp.AsyncTaskCallBack;
import com.example.countriesapp.Models.Country;
import com.example.countriesapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetCountries extends AsyncTask<Void,Void, ArrayList<Country>> {
    final String API_URL="https://restcountries.eu/rest/v2/all";
    private AsyncTaskCallBack asyncTaskCallBack;
    private ProgressDialog progressDialog;
    private Context context;
    public GetCountries(AsyncTaskCallBack asyncTaskCallBack, Context context){
        this.asyncTaskCallBack=asyncTaskCallBack;
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(context, context.getString(R.string.please_wait_task),"");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected ArrayList<Country> doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray jsonArray=new JSONArray(response.body().string());
            ArrayList<Country> countries=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String englishName=object.getString("name");
                String nativeName=object.getString("nativeName");
                String alpha3Code=object.getString("alpha3Code");
                double area=0;
                try {
                    area= object.getDouble("area");
                }catch (Exception e){

                }
                JSONArray borderCountriesArray=object.getJSONArray("borders");
                ArrayList<String> borderCountriesList=new ArrayList<>();
                for(int j=0;j<borderCountriesArray.length();j++){
                    borderCountriesList.add(borderCountriesArray.getString(j));
                }
                Country country=new Country(englishName,nativeName,alpha3Code,area,borderCountriesList);
                countries.add(country);
            }
            return countries;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            progressDialog.dismiss();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Country> countries) {
        super.onPostExecute(countries);
        asyncTaskCallBack.callBack(countries);
    }
}
