package com.example.progaleria.models;

import com.google.android.gms.maps.model.LatLng;

public class MiDto {
    private String latitud;
    private String longitud;

    public MiDto(){

    }
    public MiDto(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
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
}
