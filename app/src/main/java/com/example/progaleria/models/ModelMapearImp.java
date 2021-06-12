package com.example.progaleria.models;

import com.example.progaleria.models.interfaces.ModelMapear;
import com.example.progaleria.presenters.interfaces.PresenterModelMapear;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelMapearImp implements ModelMapear {

    private DatabaseReference referenceBache;
    private PresenterModelMapear presentador;

    public ModelMapearImp(PresenterModelMapear presentadorModel) {
        presentador = presentadorModel;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenceBache = database.getReference("BACHE");
    }

    @Override
    public void guardarCoordenadas(LatLng latLng) {
        String latitud = String.valueOf(latLng.latitude);
        String longitud = String.valueOf(latLng.longitude);

        BumpCoordinates bacheCoordenada = new BumpCoordinates();
        bacheCoordenada.setLatitud(latitud);
        bacheCoordenada.setLongitud(longitud);
        referenceBache.push().setValue(bacheCoordenada);
        presentador.onSuccess();
    }
}
