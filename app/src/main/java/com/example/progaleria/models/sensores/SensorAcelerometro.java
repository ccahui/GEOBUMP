package com.example.progaleria.models.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.example.progaleria.views.interfaces.IDeteccionBache;

public class SensorAcelerometro implements SensorEventListener {
    private float[] accelValues;
    private float mHighPassX, mHighPassY, mHighPassZ;
    private float mLastX, mLastY, mLastZ;
    private float a = 0.8f;
    private int mov = 0;
    private float zMax = 20.0f;
    private float zMin = 0.0f;


    private IDeteccionBache mapear;

    public SensorAcelerometro(IDeteccionBache mapear){
        this.mapear = mapear;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accelValues = sensorEvent.values.clone();

        float x = accelValues[0];
        float y = accelValues[1];
        float z = accelValues[2];

        mHighPassX = highPass(x, mLastX, mHighPassX);
        mHighPassY = highPass(y, mLastY, mHighPassY);
        mHighPassZ = highPass(z, mLastZ, mHighPassZ);

        mLastX = x;
        mLastY = y;
        mLastZ = z;

        boolean umb = umbral(mLastZ);
        mapear.detectbump(umb);
        //camara.handleAceleracionTotal(aceleracionTotal);
    }

    /* Filtro de Paso Alto, para enfatizar mas los cambios bruscos de aceleracion
     * del dispositivo
     */
    private float highPass(float current, float last, float filtered){
        return a * (filtered + current  - last);
    }

    private boolean umbral(float z) {
        boolean bacheDetectado = false;
        if( z < zMin && mov == 0){
            mov = 1;
        }else{
            if(z > zMax && mov == 1){
                mov++;
            }
        }
        if(mov == 2){
            bacheDetectado = true;
            mov = 0;
            System.out.println("bache detectado");
        }
        return bacheDetectado;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}
