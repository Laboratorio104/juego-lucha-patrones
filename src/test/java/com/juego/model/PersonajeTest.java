package com.juego.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juego.patrones.strategy.EstrategiaAtaque;

public class PersonajeTest {
    private Personaje guerrero;

    @BeforeEach
    public void setUp() {
        guerrero = new Personaje("Thor");
    }

    @Test
    @DisplayName("Debe crear personaje con 100 HP")
    void testCreacionPersonaje() {
        assertEquals("Thor", guerrero.getNombre());
        assertEquals(100, guerrero.getPuntosDeVida());
        assertTrue(guerrero.estaVivo());
    }

    @Test
    @DisplayName("Recibir daño reduce puntos de vida")
    void testRecibirDanoReduceVida() {
        guerrero.recibirDano(30);
        assertEquals(70, guerrero.getPuntosDeVida());
        assertTrue(guerrero.estaVivo());
    }

    @Test
    @DisplayName("HP no debe ser negativo")
    void testNegativo() {
        guerrero.recibirDano(150);
        assertEquals(0, guerrero.getPuntosDeVida());
        assertFalse(guerrero.estaVivo());
    }

    @Test
    @DisplayName("Personaje muerto cuando los puntos de vida llegan a cero")
    void testPersonajeMuerto() {
        guerrero.recibirDano(100);
        assertEquals(0, guerrero.getPuntosDeVida());
        assertFalse(guerrero.estaVivo());
    }

    @Test
    @DisplayName("Ataque por defecto usa estrategia normal y causa daño entre 10 y 30")
    void testRangoAtaque() {
        Personaje oponente = new Personaje("Loki");
        int vidaInicial = oponente.getPuntosDeVida();
        guerrero.atacar(oponente);
        int vidaFinal = oponente.getPuntosDeVida();
        int dano = vidaInicial - vidaFinal;
        assertTrue(dano >= 10 && dano <= 30, "El danio debe estar entre 10 y 30, fue:" + dano);
    }

    @Test
    @DisplayName("Cambio de estrategia usa la nueva estrategia al atacar")
    void testCambioEstrategia() {
        EstrategiaAtaque estrategiaMock = mock(EstrategiaAtaque.class);
        when(estrategiaMock.atacar()).thenReturn(12);

        guerrero.setEstrategiaAtaque(estrategiaMock);
        Personaje oponente = new Personaje("Loki");

        guerrero.atacar(oponente);

        verify(estrategiaMock).atacar();
        assertEquals(88, oponente.getPuntosDeVida());
    }
}
