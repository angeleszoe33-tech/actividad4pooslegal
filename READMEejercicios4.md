# Documentación de Ejercicios Prácticos - POO Intermedia

**Alumno:** Zoe Angeles
**Fecha:** Febrero 2026  
**Asignatura:** Programación Orientada a Objetos

## Ejercicio 1: Sistema de Gestión de Veterinaria

### Descripción general
Programa básico para una veterinaria que registra:
- Mascotas (nombre, especie, edad, peso, dueño)
- Dueños (nombre, RFC, teléfono)
- Veterinarios (nombre, número de empleado, especialidad, años de experiencia)
- Servicios (nombre, precio, duración en minutos)
- Consultas (fecha, mascota atendida, veterinario, servicio usado, costo)

### Relaciones entre clases
- Dueño – Mascota: **agregación** (un dueño puede tener varias mascotas, una mascota solo un dueño)
- Mascota – Consulta: **asociación** (la mascota recibe consultas)
- Veterinario – Consulta: **asociación** (el veterinario realiza consultas)
- Consulta – Servicio: **dependencia** (la consulta necesita un servicio)

### Características principales del código
- Cada clase tiene:
  - Constructor por defecto
  - Constructor con parámetros
  - Constructor de copia
  - Getters y setters básicos
  - Método para mostrar información
- En el main se crean objetos con datos fijos y se imprimen los resultados
- Atributos públicos en la mayoría de las clases
- No hay validaciones estrictas ni manejo de listas (por ejemplo, no hay lista de mascotas por dueño)

### Salida de ejemplo

=== Datos de la Mascota ===
Nombre: Max
Especie: Perro
Edad: 3 años
Peso: 15.5 kg
Dueño: Juan Pérez
=== Datos del Veterinario ===
Nombre: Dra. García
Especialidad: Medicina General
Experiencia: 5 años
=== Consulta Realizada ===
Fecha: 2024-01-15
Servicio: Vacunación
Costo: $350.00
text### Limitaciones
- Fecha como String (no usa fechas reales)
- No hay excepciones ni validaciones fuertes
- No se guarda nada en listas o bases de datos

## Ejercicio 2: Sistema de Gimnasio (Menú Interactivo)

### Descripción general
Menú interactivo para un gimnasio donde puedes:
1. Crear miembros
2. Crear ejercicios
3. Crear planes de entrenamiento (máximo 5 ejercicios) y asignarlos a un miembro
4. Crear entrenador (solo muestra información)

### Clases principales
- **Ejercicio**: nombre, series, repeticiones, duración por serie
- **PlanEntrenamiento**: nombre, objetivo, lista de hasta 5 ejercicios, miembro asociado
- **Miembro**: nombre, edad, tipo membresía (básica/premium), plan asignado
- **Entrenador**: nombre, especialidad, certificación

### Relaciones entre clases
- Miembro – PlanEntrenamiento: **asociación** (un miembro tiene un plan)
- PlanEntrenamiento – Ejercicio: **composición** (los ejercicios forman parte del plan)
- PlanEntrenamiento – Miembro: asociación inversa

### Características principales del código
- Menú con 5 opciones usando Scanner
- Validaciones suaves en setters (si algo está mal, pone valor por default y avisa)
- Puedes crear varios miembros y ejercicios
- Al crear plan:
  - Eliges miembro de una lista
  - Agregas ejercicios uno por uno (máximo 5 o escribes "fin")
- Calcula duración total del plan (series × repeticiones × minutos por serie)
- Imprime información formateada de miembros y planes

### Ejemplo de uso en consola
=== MENÚ GIMNASIO ===

Crear miembro
Crear ejercicio
Crear plan de entrenamiento
Crear entrenador (solo muestra)
Salir

=== CREAR MIEMBRO ===
Nombre: Ana López
Edad: 28
Tipo de membresía (básica/premium): premium
=== Datos del Miembro ===
Nombre: Ana López
Edad: 28 años
Membresía: premium
Sin plan asignado aún
Miembro creado exitosamente.
text### Limitaciones
- No relaciona entrenador con miembros o planes
- No guarda entrenadores (solo los muestra)
- No hay opción para listar todos los miembros o planes
- Validaciones no usan excepciones, solo mensajes y valores por default