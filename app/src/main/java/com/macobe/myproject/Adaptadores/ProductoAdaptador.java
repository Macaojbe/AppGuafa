package com.macobe.myproject.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.macobe.myproject.Entidades.Producto;
import com.macobe.myproject.MainActivity;
import com.macobe.myproject.MainActivity2;
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
        TextView textNameProduct = convertView.findViewById(R.id.textNameProduct);
        TextView textPriceProduct = convertView.findViewById(R.id.textPriceProduct);
        CheckBox checkBoxProduct = convertView.findViewById(R.id.checkBoxProduct);

        Producto producto = listaProductos.get(position);

        textNameProduct.setText(producto.getName());
        textPriceProduct.setText(producto.getDisplayPrice());
        checkBoxProduct.setText("Agregar Al Carrito");

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
        return convertView;
    }
}