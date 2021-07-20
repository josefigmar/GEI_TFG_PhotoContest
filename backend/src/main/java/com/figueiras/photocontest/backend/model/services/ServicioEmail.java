package com.figueiras.photocontest.backend.model.services;

import javax.mail.Address;
import javax.mail.internet.AddressException;

public interface ServicioEmail {

    void enviarMailGmail(String destinatario, String asunto, String cuerpo);
}
