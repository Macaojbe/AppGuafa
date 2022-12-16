package com.macobe.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class Maps extends AppCompatActivity {

    private MapView map;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        map = (MapView) findViewById(R.id.map);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();

        GeoPoint Colombia = new GeoPoint(4.570868, -74.297333);
        mapController.setCenter(Colombia);
        mapController.setZoom(8);
        map.setMultiTouchControls(true);

        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Toast.makeText(Maps.this, "Latitud: " + p.getLatitude() + "Longitud: " + p.getLongitude(), Toast.LENGTH_SHORT).show();
                Marker marker = new Marker(map);
                marker.setPosition(p);
                map.getOverlays().add(marker);
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                Toast.makeText(Maps.this, "Latitud: ", Toast.LENGTH_SHORT).show();
                return false;
            }
        };
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);
    }
}