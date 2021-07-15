package com.figueiras.photocontest.backend.model.exceptions;

public class CampoDuplicadoException extends InstanceException {

    public CampoDuplicadoException(String name, String key){ super(name, key); }
}
