package com.macobe.myproject.Servicios;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.macobe.myproject.Entidades.Producto;

import java.io.ByteArrayOutputStream;
import java.security.Permission;
import java.util.ArrayList;

public class ProductService {
    public byte[] imageViewToByte(ImageView imageView){
        Drawable drawable =imageView.getDrawable();
        Bitmap bitmap= Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    public ArrayList<Producto> cursorToArrayList (Cursor cursor) {
        ArrayList<Producto> list = new ArrayList<>();
        if (cursor.getCount() !=0) {
            while (cursor.moveToNext()) {
                Producto producto = new Producto(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getBlob(4)
                );
                list.add(producto);
            }
        }
        return list;
    }
}
