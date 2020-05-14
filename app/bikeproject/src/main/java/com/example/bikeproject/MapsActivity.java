package com.example.bikeproject;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Station> station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //instanciation de l'ArrayList
        station = new ArrayList<>();

        new GetStation(this).execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        rafraichirCarte();
    }

    // Affichage sur la carte
    public void rafraichirCarte(){
        if(mMap = null){
            return;
        }
        mMap.clear();
        if(!station.isEmpty()){
            for(Station station : station){
                final MarkerOptions marker = new MarkerOptions();
                LatLng latLng = new LatLng(station.getPosition().getLat(), station.getPosition().getLng());

                marker.position(latLng);
                marker.title(station.getName());

                mMap.addMarker(marker).setTag(station)
            }
        }

    }

    @Override
    public void stationChargee(ArrayList<Station> station){
        // on vide les stations et on les remplis
        this.station.clear();
        this.station.addAll(station);

        rafraichirCarte();
    }

    @Override
    public void getStationResultError(Exception exception){
        exception.printStackTrace();
        android.widget.Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
