package com.example.progaleria.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerItemSimple implements ClusterItem {
    private final LatLng position;
    private final String title;
    private final String snippet;

    public MarkerItemSimple(LatLng latLng) {
        position = latLng;
        this.title = "";
        this.snippet = "";
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }
}
