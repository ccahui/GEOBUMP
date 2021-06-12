package com.example.progaleria.presenters;

import com.example.progaleria.models.ModelMapearImp;
import com.example.progaleria.models.interfaces.ModelMapear;
import com.example.progaleria.presenters.interfaces.PresenterModelMapear;
import com.example.progaleria.presenters.interfaces.PresenterViewMapear;
import com.example.progaleria.views.interfaces.ViewMapear;
import com.google.android.gms.maps.model.LatLng;

public class PresenterImpMapear implements PresenterModelMapear, PresenterViewMapear {
    private ModelMapear modelo;
    private ViewMapear vista;

    public PresenterImpMapear(ViewMapear vista) {
        this.vista = vista;
        modelo = new ModelMapearImp(this);
        //modelo = new ModelMiCamaraImp(this);
    }

    @Override
    public void onSuccess() {
        vista.onSuccess();
        //vista.hideProgressDialog();
    }

    @Override
    public void onError(String message) {
        vista.onError(message);
        //vista.hideProgressDialog();
    }

    @Override
    public void guardarCoordena(LatLng latLng) {
        modelo.guardarCoordenadas(latLng);
        //vista.showProgressDialog();
    }
}
