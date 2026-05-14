package com.juego.model;

import com.juego.patrones.strategy.AtaqueDebil;

/**
 * Clase concreta para un Arquero.
 * Inicia con estrategia de ataque débil.
 */
public class Arquero extends Personaje {
    public Arquero(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueDebil());
    }
}