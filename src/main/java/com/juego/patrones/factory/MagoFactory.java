package com.juego.patrones.factory;

import com.juego.model.Mago;
import com.juego.model.Personaje;

/**
 * Fábrica concreta para crear Magos.
 */
public class MagoFactory extends PersonajeFactory {
    @Override
    public Personaje createPersonaje(String nombre) {
        return new Mago(nombre);
    }
}