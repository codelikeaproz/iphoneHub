package com.example.projectapp;

import android.net.Uri;

// Ensure Gadget class handles both URI and resource ID
public class Gadget {
    private String name;
    private String color;
    private String storage;
    private String price;
    private String ram;
    private String os;
    private String cpu;
    private String battery;
    private String display;
    private String camera;
    private Uri imageUri;
    private String releaseDate;
    private int imageResourceId;
    private String description;
    private String url;

    // Constructor for URI
    public Gadget(String name, String color, String storage, String price, String ram, String os, String cpu, String battery, String display, String camera, Uri imageUri, String description, String url) {
        this.name = name;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.ram = ram;
        this.os = os;
        this.cpu = cpu;
        this.battery = battery;
        this.display = display;
        this.camera = camera;
        this.imageUri = imageUri;
        this.imageResourceId = -1; // Default to -1 if no resource ID is provided
        this.description = description;
        this.url = url;
    }

    // Constructor for resource ID
    public Gadget(String name, String color, String storage, String price, String ram, String os, String cpu, String battery, String display, String camera, int imageResourceId, String description, String url) {
        this.name = name;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.ram = ram;
        this.os = os;
        this.cpu = cpu;
        this.battery = battery;
        this.display = display;
        this.camera = camera;
        //this.imageUri = null; // Default to null if no URI is provided
        this.imageResourceId = imageResourceId;
        this.description = description;
        this.url = url;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getStorage() {
        return storage;
    }

    public String getPrice() {
        return price;
    }

    public String getRam() {
        return ram;
    }

    public String getOs() {
        return os;
    }

    public String getCpu() {
        return cpu;
    }

    public String getBattery() {
        return battery;
    }

    public String getDisplay() {
        return display;
    }

    public String getCamera() {
        return camera;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public boolean hasImageUri() {
        return imageUri != null;
    }

    public boolean hasImageResourceId() {
        return imageResourceId != -1;
    }
}
