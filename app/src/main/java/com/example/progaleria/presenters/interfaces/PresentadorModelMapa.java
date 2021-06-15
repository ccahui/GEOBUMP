package com.example.progaleria.presenters.interfaces;

import com.example.progaleria.models.FotoGaleria;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface PresentadorModelMapa {
    public void onSuccessFotos(List<FotoGaleria> fotos);
    public void onSuccessPosiciones(List<LatLng> latLngs);
    public void onError(String mensaje);
}
