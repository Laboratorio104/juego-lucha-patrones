package com.juego.model;

import com.juego.patrones.strategy.AtaqueMagico;

/**
 * Clase concreta para un Mago.
 * Inicia con estrategia de ataque mágico.
 */
public class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueMagico());
    }
}