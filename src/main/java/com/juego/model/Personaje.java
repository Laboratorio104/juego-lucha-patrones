package com.juego.model;

import com.juego.patrones.strategy.AtaqueNormal;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class Personaje {
    private String nombre;
    private int puntosDeVida;
    private EstrategiaAtaque estrategiaAtaque;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.estrategiaAtaque = new AtaqueNormal(); 

}
  public void atacar(Personaje oponente) {

        int dano = estrategiaAtaque.atacar();

        oponente.recibirDano(dano);

        System.out.println(this.nombre + " usa " + estrategiaAtaque.getAttackName() + " contra " + oponente.getNombre() + " y hace " + dano + " de daño.");
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

    public void setEstrategiaAtaque(EstrategiaAtaque estrategiaAtaque) {
        this.estrategiaAtaque = estrategiaAtaque;
    }
}
