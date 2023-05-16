package com.example.final_exam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Restaurant_query#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Restaurant_query extends Fragment implements OnMapReadyCallback {

    private MapView mapView;

    public Restaurant_query() {
        // Required empty public constructor
    }

    public static Restaurant_query newInstance(String param1, String param2) {
        Restaurant_query fragment = new Restaurant_query();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurent_query, container, false);
    }

    // Implement the following method to load the Google Map service after created.
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(saveInstanceState);
        mapView.getMapAsync(this);
        // This method force the mapView to resume after assign
        mapView.onResume();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Configure the location of the destination
        LatLng destination = new LatLng(25.0338574, 121.3903071);
        googleMap.addMarker(new MarkerOptions().position(destination).title("Marker in destination"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 13.0f));
    }
}