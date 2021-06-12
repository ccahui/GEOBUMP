package com.example.progaleria.views.interfaces;

import com.example.progaleria.models.MarkerItem;
import com.example.progaleria.models.MarkerItemSimple;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface ViewMapa {
    public void addMarcadoresFotos (List<MarkerItem> markers);
    public void addMapaDeCalor(List<LatLng> latLngs);
    public void addMarcadoresSimples(List<MarkerItemSimple> markerItemSimples);
    public void toastError(String mensaje);
}
