package com.juego.model;

import com.juego.patrones.strategy.AtaqueMagico;

public class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueMagico());
    }
}