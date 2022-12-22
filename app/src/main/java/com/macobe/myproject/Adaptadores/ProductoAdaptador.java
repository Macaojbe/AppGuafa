package com.macobe.myproject.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.macobe.myproject.DB.DBFirebase;
import com.macobe.myproject.Entidades.Producto;
import com.macobe.myproject.MainActivity;
import com.macobe.myproject.MainActivity2;
import com.macobe.myproject.ProductForm;
import com.macobe.myproject.R;

import java.util.ArrayList;

public class ProductoAdaptador extends BaseAdapter {

    Context context;
    ArrayList<Producto> listaProductos;
    LayoutInflater inflater;


    public ProductoAdaptador (Context context, ArrayList<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item, null);
        }

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        TextView textNameProduct = convertView.findViewById(R.id.textNameProduct);
        TextView textPriceProduct = convertView.findViewById(R.id.textPriceProduct);

        Producto producto = listaProductos.get(position);

        textNameProduct.setText(producto.getName());
        textPriceProduct.setText(producto.getDisplayPrice());

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), MainActivity2.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", producto.getDisplayPrice());
                context.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFirebase dbFirebase;
                dbFirebase = new DBFirebase();
                dbFirebase.deleteProduct(producto.getId());
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ProductForm.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}