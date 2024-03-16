package com.gh.mp3player.travelingnote.act;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.gh.mp3player.travelingnote.App;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapMgr {
    private MapMgr instance;
    private GoogleMap map;
    private Marker marker;
    private Location location;
    private boolean isUpdate;

    public MapMgr() {

    }

    public void showMyLocation() {
        if (location == null) return;
        if (marker == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("Here");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
            marker = map.addMarker(markerOptions);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 16));
        } else if (isUpdate) {
            marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            new LatLng(location.getLatitude(), location.getLongitude());
            isUpdate = false;
        }
    }

    public void forseShowMyLocation() {
        isUpdate = false;
        showMyLocation();
    }

    public void initMap(Context context, GoogleMap googleMap) {
        this.map = googleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest request = new LocationRequest();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(request, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                location = locationResult.getLastLocation();
                showMyLocation();
            }
        }, Looper.getMainLooper());
    }

    public MapMgr getInstance() {
        if (instance == null) {
            instance = new MapMgr();
        }
        return instance;
    }
}
