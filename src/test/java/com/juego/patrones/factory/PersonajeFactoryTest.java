package com.juego.patrones.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.juego.model.Personaje;

public class PersonajeFactoryTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("GuerreroFactory crea un Guerrero con ataque fuerte (20-40)")
    void testGuerreroFactory() {
        Personaje personaje = new GuerreroFactory().createPersonaje("Bjorn");
        assertTrue(personaje instanceof com.juego.model.Guerrero);
        verificarRangoDeDano(personaje, 20, 40);
    }

    @Test
    @DisplayName("MagoFactory crea un Mago con ataque mágico (10-25)")
    void testMagoFactory() {
        Personaje personaje = new MagoFactory().createPersonaje("Merlín");
        assertTrue(personaje instanceof com.juego.model.Mago);
        verificarRangoDeDano(personaje, 10, 25);
    }

    @Test
    @DisplayName("ArqueroFactory crea un Arquero con ataque débil (5-15)")
    void testArqueroFactory() {
        Personaje personaje = new ArqueroFactory().createPersonaje("Legolas");
        assertTrue(personaje instanceof com.juego.model.Arquero);
        verificarRangoDeDano(personaje, 5, 15);
    }

    private void verificarRangoDeDano(Personaje atacante, int minDano, int maxDano) {
        Personaje oponente = new Personaje("Muñeco de Pruebas");
        int vidaInicial = oponente.getPuntosDeVida();
        
        atacante.atacar(oponente);
        int danoCausado = vidaInicial - oponente.getPuntosDeVida();

        assertTrue(danoCausado >= minDano && danoCausado <= maxDano,
                "El daño " + danoCausado + " está fuera del rango esperado (" + minDano + " - " + maxDano + ")");
    }
}