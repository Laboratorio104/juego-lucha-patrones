package com.juego.patrones.factory;

import com.juego.model.Mago;
import com.juego.model.Personaje;

public class MagoFactory extends PersonajeFactory {
    @Override
    public Personaje createPersonaje(String nombre) {
        return new Mago(nombre);
    }
}