package com.juego.model;

import com.juego.patrones.strategy.AtaqueFuerte;

public class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre);
        setEstrategiaAtaque(new AtaqueFuerte());
    }
}