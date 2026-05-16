package com.juego.patrones.factory;

import com.juego.model.Personaje;

public abstract class PersonajeFactory {

    public abstract Personaje createPersonaje(String nombre);
}