package com.macobe.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.macobe.myproject.DB.DBFirebase;
import com.macobe.myproject.DB.DBHelper;
import com.macobe.myproject.Entidades.Producto;
import com.macobe.myproject.Servicios.ProductService;

public class ProductForm extends AppCompatActivity {

    private DBHelper dbHelper;
    private DBFirebase dbFirebase;
    private ProductService productService;
    private Button btnProductForm;
    private ImageView imgProductForm;
    private EditText editNameProductForm, editDescriptionProductForm, editPriceProductForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        btnProductForm = (Button) findViewById(R.id.btnProductForm);
        imgProductForm = (ImageView) findViewById(R.id.imgProductForm);
        editNameProductForm = (EditText) findViewById(R.id.editNameProductForm);
        editDescriptionProductForm = (EditText) findViewById(R.id.editDescriptionProductForm);
        editPriceProductForm = (EditText) findViewById(R.id.editPriceProductForm);

        dbHelper = new DBHelper(this);
        dbFirebase = new DBFirebase();
        productService = new ProductService();

        btnProductForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producto producto = new Producto(
                        editNameProductForm.getText().toString(),
                        editDescriptionProductForm.getText().toString(),
                        Integer.parseInt(editPriceProductForm.getText().toString()),
                        productService.imageViewToByte(imgProductForm)
                );

                dbFirebase.insertProduct(producto);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}