[![Build Status](https://github.com/Laboratorio104/juego-lucha-patrones/actions/workflows/ci.yml/badge.svg)](https://github.com/Laboratorio104/juego-lucha-patrones/actions)
[![JaCoCo Coverage](.github/badges/jacoco.svg?v=2)](https://jacoco.org)

# Juego de Lucha por Turnos

## Arquitectura y Patrones de Diseño

El sistema implementa una arquitectura desacoplada basada en patrones creacionales y estructurales para garantizar la extensibilidad y cumplir con los principios de diseño de software.

### 1. Patrón Strategy (Estructural)

* **Por qué se eligió:** Evita la acumulación de estructuras condicionales complejas (`if-else` o `switch`) dentro de la clase `Personaje` para determinar el daño según el tipo de ataque. Permite cumplir con el principio de Abierto/Cerrado (OCP), facilitando la adición de nuevos tipos de daño sin modificar las clases existentes.
* **Flujo de ejecución:** 
  1. La interfaz `EstrategiaAtaque` define el contrato mediante el método `atacar()`.
  2. Clases concretas (`AtaqueFuerte`, `AtaqueMagico`, `AtaqueDebil`, `AtaqueNormal`) implementan dicha interfaz encapsulando sus propios rangos de daño aleatorio.
  3. La clase `Personaje` mantiene una referencia polimórfica a `EstrategiaAtaque`.
  4. Al invocar `Personaje.atacar(oponente)`, el flujo delega el cálculo del daño directamente al método `atacar()` de la estrategia asignada y aplica el resultado sobre la salud del oponente.

### 2. Patrón Factory Method (Creacional)

* **Por qué se eligió:** Desacopla al cliente de la instanciación directa de subclases de personajes (`Guerrero`, `Mago`, `Arquero`). Centraliza las reglas de inicialización, asegurando que cada rol nazca con la configuración correcta de atributos y dependencias sin exponer dicho proceso de ensamblaje de forma pública.
* **Flujo de ejecución:**
  1. La clase abstracta `PersonajeFactory` expone el método de fabricación `createPersonaje(String nombre)`.
  2. Subclases de la fábrica (`GuerreroFactory`, `MagoFactory`, `ArqueroFactory`) implementan este método.
  3. Cada fábrica concreta ejecuta internamente la instanciación de su respectivo personaje (por ejemplo, `new Mago(nombre)`).
  4. Durante el constructor del personaje concreto, se invoca `setEstrategiaAtaque()` para asignarle de manera automática la estrategia por defecto correspondiente a su rol antes de ser retornado.

## Instrucciones de Ejecución

### Requisitos
* Java 17
* Maven

### Comandos del Ciclo de Vida

1. **Compilar e instalar dependencias:**
```bash
mvn clean compile
```

2. **Ejecutar pruebas unitarias (JUnit 5 + Mockito):**
```bash
mvn test
```

3. **Generar reporte de cobertura:**
```bash
mvn jacoco:report
```
*El reporte interactivo en formato HTML se genera en la ruta: `target/site/jacoco/index.html`.*

## Sustentación del Proyecto
[Insertar enlace del video aquí]