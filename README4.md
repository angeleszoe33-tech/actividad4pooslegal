# Control Escolar - Programa en Java

**Asignatura:** Programación Orientada a Objetos 
**Alumno:** Zoe Angeles
**Fecha:** Febrero 2025  

Este documento describe el diseño, modelado y implementación de un sistema básico de control escolar en Java, siguiendo el caso práctico proporcionado.

## 1. Modelado de Abstracciones (Estructura de Clases)

Se identificaron las siguientes entidades principales del problema:

- **Profesor** → Tiene nombre, número de nómina, sueldo por hora y (opcionalmente) una materia que imparte.
- **Alumno** → Tiene matrícula, nombre, edad y un curso en el que está inscrito.
- **Materia** → Tiene nombre, clave, créditos y horas semanales.
- **Curso** → Tiene nombre y está compuesto exactamente por **tres materias**. Los créditos del curso son la suma de los créditos de sus materias.

**Relaciones clave:**
- Un **Alumno** está inscrito en **un Curso** (agregación).
- Un **Profesor** imparte **una Materia** (agregación – puede no tener materia asignada aún).
- Un **Curso** está **compuesto** por exactamente **tres Materias** (composición).

## 2. Diagrama de Secuencia (Representación textual simplificada)

Usuario → Main: Inicia el programa e ingresa datos
Main → Materia: Crea 3 instancias de Materia con datos del usuario
Main → Curso: Crea Curso con nombre y las 3 materias
Curso → Materia (x3): Consulta créditos para calcular total
Main → Alumno: Crea Alumno y le asigna el Curso
Main → Profesor: Crea Profesor y le asigna una Materia (ej. la primera)
Profesor → Materia: Consulta horas semanales para calcular sueldo
Main → Usuario: Muestra toda la información generada

## 3. Tipos de Relaciones entre Clases

| Clases involucradas     | Tipo de relación   | Explicación                                                                 |
|-------------------------|--------------------|-----------------------------------------------------------------------------|
| Curso – Materia         | **Composición**    | El curso está formado por tres materias. Las materias son parte integral del curso. Si se destruyera el curso, las materias perderían sentido en este contexto. |
| Alumno – Curso          | **Agregación**     | El alumno referencia un curso, pero el curso puede existir sin alumnos.    |
| Profesor – Materia      | **Agregación**     | El profesor referencia una materia que imparte, pero la materia puede existir sin profesor asignado. |
| Otras dependencias      | Dependencia débil  | Ejemplo: Profesor depende de métodos de Materia para calcular sueldo.      |

## 4. Descripción de las Clases Implementadas

| Clase      | Atributos principales                              | Constructores                              | Métodos principales                          | Observaciones                              |
|------------|----------------------------------------------------|--------------------------------------------|----------------------------------------------|--------------------------------------------|
| `Materia`  | nombre, clave, creditos, horasSemanales            | vacío, con parámetros, de copia            | getters, setters, `mostrarInfo()`            | Clase base simple y autónoma               |
| `Curso`    | nombre, materias (array fijo de 3)                 | vacío, con nombre + 3 materias, de copia   | `calcularCreditosTotales()`, `mostrarInfo()` | Fuerza exactamente 3 materias              |
| `Profesor` | nombre, numeroNomina, sueldoPorHora, materiaImparte| vacío, con parámetros, de copia            | `calcularSueldoSemanal()`, `mostrarInfo()`   | materiaImparte puede ser null              |
| `Alumno`   | matricula, nombre, edad, cursoInscrito             | vacío, con parámetros, de copia            | `mostrarInfo()`                              | cursoInscrito puede ser null (aunque no se usa) |

## 5. Características del Código Implementado

- **Interacción con el usuario** mediante `Scanner` (aunque no era estrictamente requerida, se incluyó para demostrar funcionalidad completa).
- Constructores solicitados en todas las clases:
  - Constructor por defecto (vacío)
  - Constructor con parámetros
  - Constructor de copia (copia superficial o profunda según el caso)
- Getters y setters para todos los atributos
- Métodos funcionales relevantes (cálculo de sueldo, cálculo de créditos totales, impresión formateada)
- Estilo **principiante/intencionalmente básico**:
  - Atributos públicos (no encapsulados estrictamente)
  - Poca validación de datos
  - Uso de `nextLine()` después de `nextInt()` para evitar problemas con el buffer
  - Comentarios explicativos y "amigables" para reflejar nivel inicial

## 6. Instrucciones para Compilar y Ejecutar

```bash
# Compilar
javac ControlEscolar.java

# Ejecutar
java ControlEscolar