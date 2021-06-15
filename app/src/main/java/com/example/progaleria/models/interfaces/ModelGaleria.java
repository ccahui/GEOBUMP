package com.example.progaleria.models.interfaces;

import com.example.progaleria.models.FotoGaleria;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public interface ModelGaleria {
    public void obtenerFotos();
}
