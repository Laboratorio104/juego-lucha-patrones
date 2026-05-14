package com.juego.patrones.strategy;

import java.util.Random;

/**
 * Estrategia de ataque débil.
 * Causa daño entre 5 y 15 puntos.
 */
public class AtaqueDebil implements EstrategiaAtaque {
    private static final int MIN_DANO = 5;
    private static final int MAX_DANO = 15;
    private final Random rand = new Random();

    @Override
    public int atacar() {
        return rand.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
    }
}