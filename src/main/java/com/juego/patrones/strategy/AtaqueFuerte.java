package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque fuerte.
 * Causa daño entre 20 y 40 puntos.
 */
public class AtaqueFuerte implements EstrategiaAtaque {
    private static final int MIN_DANO = 20;
    private static final int MAX_DANO = 40;
    private final Random rand = new Random();

    @Override
    public int atacar() {
        return rand.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
    }
}