package com.juego.patrones.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.juego.model.Personaje;

public class PersonajeFactoryTest {

    @Test
    @DisplayName("GuerreroFactory crea un Guerrero con ataque fuerte")
    void testGuerreroFactory() {
        PersonajeFactory factory = new GuerreroFactory();
        Personaje personaje = factory.createPersonaje("Bjorn");

        assertTrue(personaje instanceof com.juego.model.Guerrero);

        Personaje oponente = new Personaje("Loki");
        int vidaInicial = oponente.getPuntosDeVida();
        personaje.atacar(oponente);
        int dano = vidaInicial - oponente.getPuntosDeVida();

        assertTrue(dano >= 20 && dano <= 40,
                "Un Guerrero debe causar entre 20 y 40 de daño, fue: " + dano);
    }

    @Test
    @DisplayName("MagoFactory crea un Mago con ataque mágico")
    void testMagoFactory() {
        PersonajeFactory factory = new MagoFactory();
        Personaje personaje = factory.createPersonaje("Merlín");

        assertTrue(personaje instanceof com.juego.model.Mago);

        Personaje oponente = new Personaje("Loki");
        int vidaInicial = oponente.getPuntosDeVida();
        personaje.atacar(oponente);
        int dano = vidaInicial - oponente.getPuntosDeVida();

        assertTrue(dano >= 10 && dano <= 25,
                "Un Mago debe causar entre 10 y 25 de daño, fue: " + dano);
    }

    @Test
    @DisplayName("ArqueroFactory crea un Arquero con ataque débil")
    void testArqueroFactory() {
        PersonajeFactory factory = new ArqueroFactory();
        Personaje personaje = factory.createPersonaje("Legolas");

        assertTrue(personaje instanceof com.juego.model.Arquero);

        Personaje oponente = new Personaje("Loki");
        int vidaInicial = oponente.getPuntosDeVida();
        personaje.atacar(oponente);
        int dano = vidaInicial - oponente.getPuntosDeVida();

        assertTrue(dano >= 5 && dano <= 15,
                "Un Arquero debe causar entre 5 y 15 de daño, fue: " + dano);
    }
}
