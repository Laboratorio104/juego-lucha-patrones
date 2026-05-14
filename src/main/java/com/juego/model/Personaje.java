package com.juego.model;

import java.util.Random;

import com.juego.patrones.strategy.AtaqueNormal;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class Personaje {
    private String nombre;
    private int puntosDeVida;
    private EstrategiaAtaque estrategiaAtaque;

    private final int MAX_DANO = 30;
    private final int MIN_DANO = 10;

    private Random rand;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.rand = new Random();
        this.estrategiaAtaque = new AtaqueNormal(); // Estrategia por defecto
    
}
 public void atacar(Personaje oponente) {

        int dano = estrategiaAtaque.atacar();

        oponente.recibirDano(dano);

        System.out.println(
                this.nombre + " ataca a "
                        + oponente.getNombre()
                        + " causando "
                        + dano
                        + " puntos de daño."
        );
    }

    public void recibirDano(int dano) {

        if (dano < 0) {
            return;
        }

        this.puntosDeVida -= dano;

        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0;
        }
    }

    public boolean estaVivo() {
        return this.puntosDeVida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    /**
     * Establece la estrategia de ataque para este personaje.
     * @param estrategiaAtaque la nueva estrategia de ataque.
     */
    public void setEstrategiaAtaque(EstrategiaAtaque estrategiaAtaque) {
        this.estrategiaAtaque = estrategiaAtaque;
    }
}
