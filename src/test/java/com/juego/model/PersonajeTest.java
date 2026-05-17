package com.juego.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.juego.patrones.strategy.EstrategiaAtaque;

public class PersonajeTest {
    private Personaje personajeBase;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        personajeBase = new Personaje("Thor"); 
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Debe crear personaje con 100 HP")
    void testCreacionPersonaje() {
        assertEquals("Thor", personajeBase.getNombre());
        assertEquals(100, personajeBase.getPuntosDeVida());
        assertTrue(personajeBase.estaVivo());
    }

    @Test
    @DisplayName("Recibir daño reduce puntos de vida")
    void testRecibirDanoReduceVida() {
        personajeBase.recibirDano(30);
        assertEquals(70, personajeBase.getPuntosDeVida());
        assertTrue(personajeBase.estaVivo());
    }

    @Test
    @DisplayName("HP no debe ser negativo")
    void testNegativo() {
        personajeBase.recibirDano(150);
        assertEquals(0, personajeBase.getPuntosDeVida());
        assertFalse(personajeBase.estaVivo());
    }

    @Test
    @DisplayName("Daño negativo no afecta los HP")
    void testDanoNegativo() {
        personajeBase.recibirDano(-20);
        assertEquals(100, personajeBase.getPuntosDeVida());
    }

    @Test
    @DisplayName("Personaje muerto cuando los puntos de vida llegan a cero")
    void testPersonajeMuerto() {
        personajeBase.recibirDano(100);
        assertEquals(0, personajeBase.getPuntosDeVida());
        assertFalse(personajeBase.estaVivo());
    }

    @Test
    @DisplayName("Ataque por defecto usa estrategia normal y causa daño entre 10 y 30")
    void testRangoAtaque() {
        Personaje oponente = new Personaje("Loki");
        int vidaInicial = oponente.getPuntosDeVida();
        
        personajeBase.atacar(oponente);
        int dano = vidaInicial - oponente.getPuntosDeVida();
        
        assertTrue(dano >= 10 && dano <= 30, "El daño debe estar entre 10 y 30, fue: " + dano);
    }

    @Test
    @DisplayName("Cambio de estrategia usa la nueva estrategia al atacar")
    void testCambioEstrategia() {
        EstrategiaAtaque estrategiaMock = mock(EstrategiaAtaque.class);
        when(estrategiaMock.atacar()).thenReturn(12);
        when(estrategiaMock.getAttackName()).thenReturn("Ataque Mock");

        personajeBase.setEstrategiaAtaque(estrategiaMock);
        Personaje oponente = new Personaje("Loki");

        personajeBase.atacar(oponente);

        verify(estrategiaMock).atacar();
        assertEquals(88, oponente.getPuntosDeVida());
    }
}