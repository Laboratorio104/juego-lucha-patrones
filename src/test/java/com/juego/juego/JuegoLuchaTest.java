package com.juego.juego;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.inOrder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import com.juego.model.Personaje;

public class JuegoLuchaTest {

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
    @DisplayName("El método main ejecuta una simulación completa sin fallar")
    void testMainEjecutaSinExcepciones() {
        assertDoesNotThrow(() -> JuegoLucha.main(new String[]{}), 
                "La simulación del método main falló y lanzó una excepción");
    }

    @Test
    @DisplayName("El ciclo no se ejecuta si el defensor ya está muerto al iniciar")
    void testCombateConDefensorMuerto() {
        Personaje atacante = new Personaje("Thor");
        Personaje defensor = new Personaje("Loki");
        defensor.recibirDano(150); 
        
        new JuegoLucha(atacante, defensor).iniciarCombate();
        
        assertEquals(100, atacante.getPuntosDeVida(), "El atacante no debió realizar ninguna acción");
    }

    @Test
    @DisplayName("El ciclo no se ejecuta si el atacante inicial ya está muerto")
    void testCombateConAtacanteMuerto() {
        Personaje atacante = new Personaje("Thor");
        Personaje defensor = new Personaje("Loki");
        atacante.recibirDano(150); 
        
        new JuegoLucha(atacante, defensor).iniciarCombate();
        
        assertEquals(100, defensor.getPuntosDeVida(), "El defensor no debió recibir daño");
    }

    @Test
    @DisplayName("Combate completo alterna turnos de forma lógica y termina cuando alguien muere")
    void testCombateCompletoYAlternaTurnos() {
        Personaje thor = spy(new Personaje("Thor"));
        Personaje loki = spy(new Personaje("Loki"));

        new JuegoLucha(thor, loki).iniciarCombate();

        assertTrue(!thor.estaVivo() || !loki.estaVivo(), "El combate debió terminar con un personaje muerto");

        InOrder orden = inOrder(thor, loki);
        orden.verify(thor).atacar(loki);
        orden.verify(loki).atacar(thor);
        orden.verify(thor).atacar(loki);
    }
}