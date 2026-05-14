package com.juego.juego;

import com.juego.model.Personaje;

/**
 * Simula un combate por turnos entre dos personajes.
 * Cada turno un personaje ataca al otro usando su estrategia de ataque actual.
 */
public class JuegoLucha {
    private final Personaje personaje1;
    private final Personaje personaje2;

    public JuegoLucha(Personaje personaje1, Personaje personaje2) {
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
    }

    /**
     * Inicia la simulación de combate por turnos hasta que un personaje muera.
     */
    public void iniciarCombate() {
        System.out.println("--- INICIO DEL COMBATE ---");
        System.out.println(personaje1.getNombre() + " vs " + personaje2.getNombre());
        System.out.println(personaje1.getNombre() + " tiene " + personaje1.getPuntosDeVida() + " HP.");
        System.out.println(personaje2.getNombre() + " tiene " + personaje2.getPuntosDeVida() + " HP.");
        System.out.println();

        Personaje atacante = personaje1;
        Personaje defensor = personaje2;
        int turno = 1;

        while (atacante.estaVivo() && defensor.estaVivo()) {
            System.out.println("Turno " + turno + ": " + atacante.getNombre() + " ataca a " + defensor.getNombre());
            atacante.atacar(defensor);
            System.out.println(defensor.getNombre() + " queda con " + defensor.getPuntosDeVida() + " HP.");
            System.out.println();

            if (!defensor.estaVivo()) {
                System.out.println("¡" + defensor.getNombre() + " ha muerto!");
                System.out.println("Ganador: " + atacante.getNombre());
                break;
            }

            Personaje aux = atacante;
            atacante = defensor;
            defensor = aux;
            turno++;
        }

        System.out.println("--- FIN DEL COMBATE ---");
    }

    public static void main(String[] args) {
        System.out.println("Use una fábrica o cree personajes y ejecute iniciarCombate().");
    }
}
