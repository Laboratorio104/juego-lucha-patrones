package com.juego.patrones.strategy;

public interface EstrategiaAtaque {
    /**
     * Calcula y retorna el daño causado por el ataque.
     * @return el daño como un entero positivo.
     */
    int atacar();
}