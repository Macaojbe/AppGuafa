package com.macobe.myproject.Entidades;

import java.util.UUID;

public class Producto {
    private String id;
    private String name;
    private String description;
    private int price;
    private String image;
    private String latitude;
    private String longitude;

    public Producto(String id, String name, String description, int price, String image, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Producto(String name, String description, int price, String image, String latitude, String longitude) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {return latitude;}

    public void setLatitude(String latitude) {this.latitude = latitude;}

    public String getLongitude() {return longitude;}

    public void setLongitude(String longitude) {this.longitude = longitude;}

    public String getId() {return id;}

    public String getImage() {
        return image;
    }

    public void setId(String id) {this.id = id;}

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
