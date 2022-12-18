package com.macobe.myproject.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
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
        prod.put("image", producto.getImage());
        prod.put("latitude", producto.getLatitude());
        prod.put("longitude", producto.getLongitude());

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
                                Producto product = new Producto(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("price").toString()),
                                        document.getData().get("image").toString(),
                                        document.getData().get("latitude").toString(),
                                        document.getData().get("longitude").toString()
                                );
                                listaProductos.add(product);
                            }
                            adaptador.notifyDataSetChanged();
                        } else {
                            Log.e("Error document", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void updateProduct (Producto producto) {
        db.collection("products").whereEqualTo("id", producto.getId())
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document =
                                    task.getResult().getDocuments().get(0);

                            document.getReference().update(
                                    "name", producto.getName(),
                                    "description", producto.getDescription(),
                                    "price", producto.getDisplayPrice(),
                                    "image", producto.getImage(),
                                    "latitud", producto.getLatitude(),
                                    "longitud", producto.getLongitude()
                            );
                        }
                    }
                });
    }

    public void deleteProduct (String id) {
        db.collection("products").whereEqualTo("id", id)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            document.getReference().delete();
                        }
                    }
                });
    }
}