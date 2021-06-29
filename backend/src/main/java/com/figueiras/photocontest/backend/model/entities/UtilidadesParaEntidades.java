package com.figueiras.photocontest.backend.model.entities;

public class UtilidadesParaEntidades {

    public static String[] separarPorEspacios(String cadena){
        if(cadena == null || cadena.length() == 0){
            return new String[0];
        }
        return cadena.split("\\s");
    }
}
