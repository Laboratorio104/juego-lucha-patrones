package com.juego.juego;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.juego.model.Personaje;
import com.juego.patrones.strategy.EstrategiaAtaque;

public class JuegoLuchaTest {

    @Test
    @DisplayName("El combate termina cuando el defensor muere")
    void testTerminaCuandoDefensorMuere() {
        Personaje atacante = new PersonajeStub("Guerrero", 150);
        Personaje defensor = new Personaje("Loki");

        PrintStream original = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            new JuegoLucha(atacante, defensor).iniciarCombate();
        } finally {
            System.setOut(original);
        }

        assertTrue(atacante.estaVivo());
        assertFalse(defensor.estaVivo());
        assertTrue(defensor.getPuntosDeVida() == 0);
    }

    @Test
    @DisplayName("Se alternan los turnos entre los personajes")
    void testAlternaTurnos() {
        StringBuilder log = new StringBuilder();
        Personaje thor = new PersonajeStub("Thor", log, 10, 100);
        Personaje loki = new PersonajeStub("Loki", log, 5, 100);

        PrintStream original = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            new JuegoLucha(thor, loki).iniciarCombate();
        } finally {
            System.setOut(original);
        }

        String orden = log.toString();
        assertTrue(orden.startsWith("Thor->Loki;"));
        assertTrue(orden.contains("Loki->Thor;"));
        assertTrue(orden.contains("Thor->Loki;"));
    }

    @Test
    @DisplayName("Combate completo usa estrategias deterministas y muestra rotacion correcta")
    void testCombateCompletoConEstrategias() {
        Personaje guerrero = new Personaje("Thor");
        Personaje loki = new Personaje("Loki");
        guerrero.setEstrategiaAtaque(new EstrategiaFija(30));
        loki.setEstrategiaAtaque(new EstrategiaFija(10));

        PrintStream original = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            new JuegoLucha(guerrero, loki).iniciarCombate();
        } finally {
            System.setOut(original);
        }

        assertTrue(guerrero.estaVivo());
        assertFalse(loki.estaVivo());
        assertTrue(loki.getPuntosDeVida() == 0);
    }

    @Test
    @DisplayName("Main imprime instrucciones de uso")
    void testMainImprimeInstrucciones() {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(salida));
        try {
            JuegoLucha.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        assertTrue(salida.toString().contains("Use una fábrica o cree personajes"));
    }

    private static class PersonajeStub extends Personaje {
        private final int[] daños;
        private int index = 0;
        private final StringBuilder log;

        PersonajeStub(String nombre, int... daños) {
            super(nombre);
            this.daños = daños;
            this.log = null;
        }

        PersonajeStub(String nombre, StringBuilder log, int... daños) {
            super(nombre);
            this.daños = daños;
            this.log = log;
        }

        @Override
        public void atacar(Personaje oponente) {
            int dano = index < daños.length ? daños[index] : daños[daños.length - 1];
            index++;
            if (log != null) {
                log.append(getNombre()).append("->").append(oponente.getNombre()).append(";");
            }
            oponente.recibirDano(dano);
        }
    }

    private static class EstrategiaFija implements EstrategiaAtaque {
        private final int daño;

        EstrategiaFija(int daño) {
            this.daño = daño;
        }

        @Override
        public int atacar() {
            return daño;
        }

        @Override
        public String getAttackName() {
            return "Ataque Fijo";
        }
    }
}
