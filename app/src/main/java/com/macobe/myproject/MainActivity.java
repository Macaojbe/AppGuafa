package com.macobe.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.macobe.myproject.Adaptadores.ProductoAdaptador;
import com.macobe.myproject.DB.DBFirebase;
import com.macobe.myproject.DB.DBHelper;
import com.macobe.myproject.Entidades.Producto;
import com.macobe.myproject.Servicios.ProductService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    private ProductService productService;
    private ListView listViewProducts;
    private ArrayList<Producto> listaProductos;
    private ProductoAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        listaProductos = new ArrayList<>();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
            productService = new ProductService();
            listaProductos = productService.cursorToArrayList(dbHelper.getProducts());
        } catch (Exception e) {
            Log.e("DB get", e.toString());
        }

        adaptador = new ProductoAdaptador(getApplicationContext(), listaProductos);
        listViewProducts.setAdapter(adaptador);

        dbFirebase.getProducts(adaptador, listaProductos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAdd:
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
                startActivity(intent);
                return true;
            case R.id.itemFavorite:
                Toast.makeText(getApplicationContext(), "Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemShare:
                Toast.makeText(getApplicationContext(), "Compartir", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}