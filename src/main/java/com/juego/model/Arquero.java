package com.juego.model;

import com.juego.patrones.strategy.AtaqueDebil;

public class Arquero extends Personaje {
    public Arquero(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueDebil());
    }
}