package com.figueiras.photocontest.backend.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioSegundoPlano {

    private static ServicioConcurso servicioConcurso;

    @Autowired
    public void setServicioConcurso(ServicioConcurso servicioConcurso) {
        ServicioSegundoPlano.servicioConcurso = servicioConcurso;
    }

    private static final int NUMERO_THREADS = 1;
    private static final int RETARDO_INICIAL = 5;
    private static final int PERIODO_ENTRE_EJECUCIONES = 10;


    public static class ComprobacionDeEstadoDeConcurso implements Runnable {

        @Override
        public void run(){
            try {
                servicioConcurso.comprobarYRealizarCambiosDeEstado();
                System.out.println("Contest status changue check done at : " + new Date());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void correrComprobacionDeEstadoConcursos(){
        ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(NUMERO_THREADS);
        ServicioSegundoPlano.ComprobacionDeEstadoDeConcurso tarea =
                new ServicioSegundoPlano.ComprobacionDeEstadoDeConcurso();

        System.out.println("The time is : " + new Date());

        ScheduledFuture<?> result = ejecutor.scheduleAtFixedRate(tarea, RETARDO_INICIAL, PERIODO_ENTRE_EJECUCIONES,
                TimeUnit.SECONDS);

    }

}
