package com.example.progaleria.presenters;

import android.util.Log;

import com.example.progaleria.models.FotoGaleria;
import com.example.progaleria.models.MarkerItemSimple;
import com.example.progaleria.models.interfaces.ModelMapa;
import com.example.progaleria.models.ModeloImpMapa;
import com.example.progaleria.models.MarkerItem;
import com.example.progaleria.presenters.interfaces.PresentadorModelMapa;
import com.example.progaleria.presenters.interfaces.PresentadorViewMapa;
import com.example.progaleria.views.interfaces.ViewMapa;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PresentadorImpMapa implements PresentadorViewMapa, PresentadorModelMapa {
    public String TAG = "Presentador";
    public final ViewMapa vista;
    public final ModelMapa modelo;

    public PresentadorImpMapa(ViewMapa vista){
        this.vista = vista;
        modelo = new ModeloImpMapa(this);
    }


    @Override
    public void obtenerFotos() {
        modelo.obtenerFotos();
        Log.i(TAG, "Obteniendo fotos ....");
    }
    @Override
    public void obtenerPosiciones() {
        modelo.obtenerPosiciones();
        Log.i(TAG, "Obteniendo fotos ....");
    }

    @Override
    public void onSuccessFotos(List<FotoGaleria> fotos) {
        List<MarkerItem> markers = new ArrayList<>();
        List<MarkerItemSimple> markersSimple = new ArrayList<>();
        List<LatLng> latLngs = new ArrayList<>();
        for(FotoGaleria foto: fotos){
                double latitud = Double.parseDouble(foto.getLatitud());
                double longitud = Double.parseDouble(foto.getLongitud());
                LatLng ubicacion = new LatLng(latitud, longitud);
                MarkerItem marker = new MarkerItem(ubicacion, foto.getUrl());
                MarkerItemSimple markerSimple = new MarkerItemSimple(ubicacion);
                markers.add(marker);
                latLngs.add(ubicacion);
                markersSimple.add(markerSimple);
        }
        //vista.addMarcadoresFotos(markers);
        vista.addMarcadoresSimples(markersSimple);
        vista.addMapaDeCalor(latLngs);
        Log.i(TAG, "Se obtuvieron Exitosamente las fotos ");
    }

    @Override
    public void onSuccessPosiciones(List<LatLng> latLngs) {
        List<MarkerItemSimple> markersSimple = new ArrayList<>();
        for(LatLng latLng: latLngs){
            MarkerItemSimple markerSimple = new MarkerItemSimple(latLng);
            markersSimple.add(markerSimple);
        }
        vista.addMarcadoresSimples(markersSimple);
        vista.addMapaDeCalor(latLngs);
    }

    @Override
    public void onError(String mensaje) {
        vista.toastError(mensaje);
    }
}
