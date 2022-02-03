package com.jithu.newone;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jithu.newone.adapter.ParseItemAdapter;
import com.jithu.newone.model.ParseItemModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class home extends Fragment {

    private static final String TAG = "Tagggg";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private final List<ParseItemModel> parseItemModelList = new ArrayList<>();
    Elements data;
    Elements data1;
    Document document;
    Document document1;
    LocationManager locationManager;
    String latitude, longitude;
    private static final int REQUEST_LOCATION = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
            recyclerView = view.findViewById(R.id.recyclerView_id);
            parseItemAdapter = new ParseItemAdapter((ArrayList<ParseItemModel>) parseItemModelList, getActivity().getApplicationContext());
            recyclerView.setAdapter(parseItemAdapter);

            Content content= new Content();
            content.execute();
        }
        return view;
    }



    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            System.out.println("1");
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            System.out.println("2");
            if (locationGPS != null) {

                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = Double.toString(lat);


                longitude = Double.toString(longi);
                System.out.println(latitude+"jithu"+longitude);
                System.out.println("3");

            } else {
                System.out.println("4");
                Toast.makeText(getActivity(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPostExecute(Void unused) {
            super.onPreExecute();

            Log.i(TAG, "onPostExecute: ");

            for (int i = 0; i < parseItemModelList.size(); i++) {
                Log.i(TAG, parseItemModelList.get(i).getDirection());
            }


            parseItemAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url="https://www.google.com/search?q=petrol+pump&tbm=lcl";
                String url1="https://www.google.com/search?q=petrol+pump+"+latitude+"+"+longitude+"&tbm=lcl";
                System.out.println(url1);
                document= Jsoup.connect(url).get();
                document1= Jsoup.connect(url1).get();
                data=document.select("div.rlfl__tls > div");
                data1=document1.select("div.rlfl__tls > div");


                int size=data.size();

                if (size > 6){
                    size = 6;
                }
                for (int i =0; i <size; i++){

                    Element a = data1.select("div.uMdZh").get(i);
                    Element s = a.select("div.rllt__details").first();

                    Log.i(TAG, s.toString());

                    String name = s.select("div.dbg0pd > span").text();

                    String rating = s.select("span.MvDXgc").text();
                    String status = s.select("div").last().text();

                    String dir = a.select("div.VkpGBb > a.yYlJEf").attr("data-url");


                    Log.i(TAG, "dir: " + dir);
                    ParseItemModel temp = new ParseItemModel(name, rating, status,dir);
                    parseItemModelList.add(temp);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}