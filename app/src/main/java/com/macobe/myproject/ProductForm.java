package com.macobe.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.macobe.myproject.DB.DBFirebase;
import com.macobe.myproject.DB.DBHelper;
import com.macobe.myproject.Entidades.Producto;
import com.macobe.myproject.Servicios.ProductService;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class ProductForm extends AppCompatActivity {

    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    private ProductService productService;
    private Button btnProductForm;
    private ImageView imgProductForm;
    private EditText editNameProductForm, editDescriptionProductForm, editPriceProductForm;
    private String latitude;
    private String longitude;
    private MapView map;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        latitude = "0";
        longitude = "0";
        btnProductForm = (Button) findViewById(R.id.btnProductForm);
        imgProductForm = (ImageView) findViewById(R.id.imgProductForm);
        editNameProductForm = (EditText) findViewById(R.id.editNameProductForm);
        editDescriptionProductForm = (EditText) findViewById(R.id.editDescriptionProductForm);
        editPriceProductForm = (EditText) findViewById(R.id.editPriceProductForm);

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
        }catch (Exception e){
            Log.e("Error DB", e.toString());
        }
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        map = (MapView) findViewById(R.id.mapForm);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();

        GeoPoint Colombia = new GeoPoint(4.570868, -74.297333);
        mapController.setCenter(Colombia);
        mapController.setZoom(8);
        map.setMultiTouchControls(true);

        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Marker marker = new Marker(map);
                marker.setPosition(p);
                map.getOverlays().add(marker);
                latitude = String.valueOf(p.getLatitude());
                longitude = String.valueOf(p.getLongitude());
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);

        btnProductForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producto producto = new Producto(
                        editNameProductForm.getText().toString(),
                        editDescriptionProductForm.getText().toString(),
                        Integer.parseInt(editPriceProductForm.getText().toString().trim()),
                        "",
                        latitude,
                        longitude
                );

                dbFirebase.insertProduct(producto);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}