package com.juego.patrones.strategy;

/**
 * Interfaz para estrategias de ataque.
 * Define el método para calcular el daño de un ataque.
 */
public interface EstrategiaAtaque {
    /**
     * Calcula y retorna el daño causado por el ataque.
     * @return el daño como un entero positivo.
     */
    int atacar();
}