package com.example.progaleria.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.progaleria.R;
import com.example.progaleria.models.sensores.Localizacion;
import com.example.progaleria.models.sensores.SensorAcelerometro;
import com.example.progaleria.presenters.PresenterImpMapear;
import com.example.progaleria.presenters.interfaces.PresenterViewMapear;
import com.example.progaleria.views.interfaces.IDeteccionBache;
import com.example.progaleria.views.interfaces.ViewMapear;
import com.google.android.gms.maps.model.LatLng;

public class MapearActivity extends AppCompatActivity implements IDeteccionBache, ViewMapear {

    private LocationManager locationManager;
    private Localizacion localizacion;

    private SensorManager sensorManager;
    private SensorAcelerometro sensorAcelerometro;

    private Toast message;
    private static final String TOAST_MENSAJE = "Bache detectado";
    private static final String TAG = "DetectarBache";
    private ProgressDialog progressDialog;

    private static String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    private PresenterViewMapear presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapear);
        setTitle("Mapear");

        presentador = new PresenterImpMapear(this);

        initSensorMovimiento();
        initSensorGPS();
        initToast();
        initProgressDialog();

    }

    @Override
    public void detectbump(boolean umbral) {
        if (umbral) {
            message.show();
            progressDialog.show();
            LatLng ubicacion = localizacion.getLastLatLng();
            presentador.guardarCoordena(ubicacion);
        } else {
            message.cancel();
        }
    }
    private void initToast(){
        message = Toast.makeText(this, TOAST_MENSAJE, Toast.LENGTH_SHORT);
        message.setGravity(Gravity.CENTER, 0, 0);
    }
    private void initProgressDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Subiendo Coordenadas");
    }


    private void initSensorMovimiento(){
        sensorManager  = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAcelerometro = new SensorAcelerometro(this);
    }
    public void listenerSensorAcelerometro() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                sensorAcelerometro,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregisterListenerAcelerometro() {
        sensorManager.unregisterListener(sensorAcelerometro);
    }


    public void initSensorGPS() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        localizacion = new Localizacion();
    }
    public void listenerGPS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG","dsadsaasd");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, localizacion);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizacion);
    }
    public void unregisterListenerGPS(){
        locationManager.removeUpdates(localizacion);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listenerSensorAcelerometro();
        listenerGPS();
    }

    @Override
    protected void onPause() {
        super.onPause();
        message.cancel();
        unregisterListenerAcelerometro();
        unregisterListenerGPS();

    }

    @Override
    public void onSuccess() {
        Log.i(TAG,"bache detectado subido exitosamente");
        message.cancel();
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"bache detectado subido exitosamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
/*
    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }
*/
}