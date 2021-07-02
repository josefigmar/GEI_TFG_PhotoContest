package com.figueiras.photocontest.backend.model.exceptions;

public class InstanceNotFoundException extends InstanceException{

    public InstanceNotFoundException(String name, Object key){super(name, key);}
}
