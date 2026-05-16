package com.juego.juego;

import com.juego.model.Personaje;

public class JuegoLucha {
    private final Personaje personaje1;
    private final Personaje personaje2;

    public JuegoLucha(Personaje personaje1, Personaje personaje2) {
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
    }

    public void iniciarCombate() {
        System.out.println("INICIANDO PELEA");
        System.out.println(personaje1.getNombre() + " vs " + personaje2.getNombre());
        System.out.println(personaje1.getNombre() + " tiene " + personaje1.getPuntosDeVida() + " HP.");
        System.out.println(personaje2.getNombre() + " tiene " + personaje2.getPuntosDeVida() + " HP.");
        System.out.println();

        Personaje atacante = personaje1;
        Personaje defensor = personaje2;
        int turno = 1;

        while (atacante.estaVivo() && defensor.estaVivo()) {
            System.out.println("Turno " + turno + ". Ataca " + atacante.getNombre());
            atacante.atacar(defensor);
            System.out.println(defensor.getNombre() + " queda con " + defensor.getPuntosDeVida() + " de vida.");
            System.out.println();

            if (!defensor.estaVivo()) {
                System.out.println(defensor.getNombre() + " perdio toda su vida.");
                System.out.println("Gana " + atacante.getNombre());
                break;
            }

            Personaje aux = atacante;
            atacante = defensor;
            defensor = aux;
            turno++;
        }

        System.out.println("Pelea terminada.");
    }

    public static void main(String[] args) {
        System.out.println("Use una fábrica o cree personajes y ejecute iniciarCombate().");
    }
}
