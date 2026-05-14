package com.juego.model;

import com.juego.patrones.strategy.AtaqueFuerte;

/**
 * Clase concreta para un Guerrero.
 * Inicia con estrategia de ataque fuerte.
 */
public class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueFuerte());
    }
}