package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque mágico.
 * Causa daño entre 10 y 25 puntos.
 */
public class AtaqueMagico implements EstrategiaAtaque {
    private static final int MIN_DANO = 10;
    private static final int MAX_DANO = 25;
    private final Random rand = new Random();

    @Override
    public int atacar() {
        return rand.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
    }
}