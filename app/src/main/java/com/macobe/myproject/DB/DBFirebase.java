package com.macobe.myproject.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.macobe.myproject.Adaptadores.ProductoAdaptador;
import com.macobe.myproject.Entidades.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBFirebase {

    private FirebaseFirestore db;

    public DBFirebase( ) {
        this.db = FirebaseFirestore.getInstance();
    }

    public void insertProduct (Producto producto) {
        Map<String, Object> prod = new HashMap<>();
        prod.put("id", producto.getId());
        prod.put("name", producto.getName());
        prod.put("description", producto.getDescription());
        prod.put("price", producto.getPrice());

        db.collection("products").add(prod);
    }

    public void getProducts(ProductoAdaptador adaptador, ArrayList<Producto> listaProductos) {
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Producto product = new Producto (
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("price").toString())
                                );
                                listaProductos.add(product);
                            }
                            adaptador.notifyDataSetChanged();
                        }
                    }
                });
    }
}
