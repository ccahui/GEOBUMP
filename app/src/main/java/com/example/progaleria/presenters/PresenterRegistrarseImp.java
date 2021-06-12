package com.example.progaleria.presenters;

import android.util.Patterns;

import com.example.progaleria.models.interfaces.ModelRegistrarse;
import com.example.progaleria.models.ModeloRegistrarseImp;
import com.example.progaleria.presenters.interfaces.PresenterModelRegistrarse;
import com.example.progaleria.presenters.interfaces.PresenterViewRegistrarse;
import com.example.progaleria.views.interfaces.ViewRegistrarse;

public class PresenterRegistrarseImp implements PresenterViewRegistrarse, PresenterModelRegistrarse {

    private final String REQUIRED = "required";

    private ViewRegistrarse vista;
    private ModelRegistrarse modelo;

    public PresenterRegistrarseImp(ViewRegistrarse vista) {
        this.vista = vista;
        modelo = new ModeloRegistrarseImp(this);
    }

    @Override
    public void registrarUsuario(String nombre, String apellido, String email, String password) {
        if(validarDatosDeUsuario(nombre, apellido, email, password)){
            vista.mostrarProgressBar();
            modelo.registrarUsuario(nombre, apellido, email, password);
        }
    }

    @Override
    public void onSuccess() {
        vista.ocultarProgressBar();
        vista.redirecToHome();
    }

    @Override
    public void onError(String error) {
        vista.ocultarProgressBar();
        vista.onErrorRegistrarse(error);
    }

    private boolean validarDatosDeUsuario(String nombre, String apellido, String email, String password) {
        return validarEmail(email) & validarPassword(password) & validarNombre(nombre) & validarApellido(apellido);
    }

    private boolean validarNombre(String nombre){
        if(isEmpty(nombre)){
            vista.setNombreError(REQUIRED);
            return false;
        }
        return true;
    }

    private boolean validarApellido(String apellido){
        if(isEmpty(apellido)){
            vista.setApellidoError(REQUIRED);
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        boolean valido = true;

        if (isEmpty(email)) {
            vista.setEmailError(REQUIRED);
            valido = false;
        } else if (! isEmailValid(email)) {
            vista.setEmailError("email no valido");
            valido = false;
        }
        return valido;
    }

    private boolean validarPassword(String password) {
        boolean valido = true;

        if (isEmpty(password)) {
            vista.setPassworError(REQUIRED);
            valido = false;
        } else if (password.length() < 6) {
            vista.setPassworError("password > 6");
            valido = false;
        }
        return valido;
    }

    private boolean isEmpty(String cadena){
        if("".equals(cadena))
            return true;
        return false;
    }

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
