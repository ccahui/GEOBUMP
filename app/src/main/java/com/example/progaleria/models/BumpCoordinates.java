package com.example.progaleria.models;

public class BumpCoordinates {
    private String id;
    private String latitud;
    private String longitud;
    private String lugarDescripcion;

    public BumpCoordinates(){

    }

    public BumpCoordinates(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLugarDescripcion() {
        return lugarDescripcion;
    }

    public void setLugarDescripcion(String lugarDescripcion) {
        this.lugarDescripcion = lugarDescripcion;
    }
}
