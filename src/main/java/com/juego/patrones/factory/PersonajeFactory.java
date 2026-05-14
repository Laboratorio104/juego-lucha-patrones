package com.juego.patrones.factory;

import com.juego.model.Personaje;

/**
 * Fábrica abstracta para crear personajes.
 * Utiliza el patrón Factory Method para encapsular la creación de personajes.
 */
public abstract class PersonajeFactory {
    /**
     * Método factory para crear un personaje con el nombre especificado.
     * @param nombre el nombre del personaje.
     * @return una instancia de Personaje.
     */
    public abstract Personaje createPersonaje(String nombre);
}