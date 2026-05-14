# Juego Lucha Patrones

Proyecto Java Maven que implementa un juego de combate por turnos usando patrones de diseño.

## Descripción

`Juego-lucha-patrones` es una aplicación de ejemplo para un juego de lucha en el que dos personajes se enfrentan por turnos hasta que uno de ellos muere. El proyecto demuestra el uso de patrones de diseño para separar responsabilidades y favorecer la extensibilidad.

## Patrones de diseño

### Strategy

El patrón Strategy se utiliza para encapsular distintas formas de atacar en clases independientes.

- `com.juego.patrones.strategy.EstrategiaAtaque`
- `com.juego.patrones.strategy.AtaqueFuerte`
- `com.juego.patrones.strategy.AtaqueDebil`
- `com.juego.patrones.strategy.AtaqueMagico`
- `com.juego.patrones.strategy.AtaqueNormal`

La clase `com.juego.model.Personaje` mantiene una referencia a `EstrategiaAtaque` y usa `setEstrategiaAtaque(...)` para cambiar el comportamiento en tiempo de ejecución.

### Factory Method

El patrón Factory Method se usa para crear personajes con comportamiento inicial especializado.

- `com.juego.patrones.factory.PersonajeFactory`
- `com.juego.patrones.factory.GuerreroFactory`
- `com.juego.patrones.factory.MagoFactory`
- `com.juego.patrones.factory.ArqueroFactory`

Las fábricas concretas crean personajes con estrategias de ataque predeterminadas.

## Pruebas

El proyecto incluye pruebas unitarias con JUnit 5 y Mockito.

- `src/test/java/com/juego/model/PersonajeTest.java`
- `src/test/java/com/juego/patrones/factory/PersonajeFactoryTest.java`
- `src/test/java/com/juego/juego/JuegoLuchaTest.java`

Estas pruebas cubren:

- creación y estado de los personajes
- reducción de vida y muerte
- uso del patrón Strategy
- creación de personajes por fábricas
- simulación de combate por turnos

## Cobertura de código

JaCoCo se usa para generar informes de cobertura.

- Configuración en `pom.xml`
- Reporte generado en `target/site/jacoco/index.html`

## Integración continua

Se incluye un workflow de GitHub Actions en `.github/workflows/ci.yml` que ejecuta:

1. compilación del proyecto con Maven
2. ejecución de pruebas
3. generación del reporte JaCoCo

## Instrucciones de ejecución

### Requisitos

- Java 17
- Maven

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar pruebas

```bash
mvn test
```

### Generar el reporte JaCoCo

```bash
mvn jacoco:report
```

### Ejecutar el proyecto

La clase principal de simulación es `com.juego.juego.JuegoLucha`.

Puedes ejecutar el proyecto con Maven si agregas una clase `main` que cree personajes y llame a `iniciarCombate()`, o usar un plugin de Maven apropiado para ejecutar la aplicación.

## Estructura del proyecto

```
├── pom.xml
├── README.md
├── .github/
│   └── workflows/
│       └── ci.yml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/juego/
│   │           ├── juego/
│   │           │   └── JuegoLucha.java
│   │           ├── model/
│   │           │   ├── Personaje.java
│   │           │   ├── Guerrero.java
│   │           │   ├── Mago.java
│   │           │   └── Arquero.java
│   │           └── patrones/
│   │               ├── factory/
│   │               │   ├── PersonajeFactory.java
│   │               │   ├── GuerreroFactory.java
│   │               │   ├── MagoFactory.java
│   │               │   └── ArqueroFactory.java
│   │               └── strategy/
│   │                   ├── EstrategiaAtaque.java
│   │                   ├── AtaqueDebil.java
│   │                   ├── AtaqueFuerte.java
│   │                   ├── AtaqueMagico.java
│   │                   └── AtaqueNormal.java
│   └── test/
│       └── java/
│           └── com/juego/
│               ├── model/
│               │   ├── PersonajeTest.java
│               ├── patrones/
│               │   └── factory/
│               │       └── PersonajeFactoryTest.java
│               └── juego/
│                   └── JuegoLuchaTest.java
```

## Notas

- El proyecto está diseñado para ser fácil de extender con nuevas estrategias y tipos de personajes.
- La separación de responsabilidades permite añadir más patrones sin modificar las clases principales.
