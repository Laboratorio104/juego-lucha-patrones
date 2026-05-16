package com.juego.patrones.strategy;

import java.util.Random;

public class AtaqueMagico implements EstrategiaAtaque {
    private static final int MIN_DANO = 10;
    private static final int MAX_DANO = 25;
    private final Random rand = new Random();

    @Override
    public int atacar() {
        return rand.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
    }

    @Override
    public String getAttackName() {
        return "Ataque Mágico";
    }
}