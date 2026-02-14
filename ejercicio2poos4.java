import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Ejercicio - Representa un ejercicio físico
 */
public class Ejercicio {
    private String nombre;
    private int series;
    private int repeticiones;
    private int duracionMinutos;

    // Constructor por defecto (cuando no sabemos nada)
    public Ejercicio() {
        this.nombre = "Sin nombre";
        this.series = 0;
        this.repeticiones = 0;
        this.duracionMinutos = 0;
    }

    // Constructor con todo lo que necesita el ejercicio
    public Ejercicio(String nombre, int series, int repeticiones, int duracionMinutos) {
        this.nombre = nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.duracionMinutos = duracionMinutos;
    }

    // Constructor de copia (para no compartir el mismo ejercicio)
    public Ejercicio(Ejercicio otro) {
        this.nombre = otro.nombre;
        this.series = otro.series;
        this.repeticiones = otro.repeticiones;
        this.duracionMinutos = otro.duracionMinutos;
    }

    // Getters y setters con validaciones básicas (no muy estrictas, pero algo)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío. Se queda 'Sin nombre'");
            this.nombre = "Sin nombre";
        } else {
            this.nombre = nombre;
        }
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        if (series <= 0) {
            System.out.println("Las series deben ser mayor a 0. Se pone 1 por default");
            this.series = 1;
        } else {
            this.series = series;
        }
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        if (repeticiones <= 0) {
            System.out.println("Las repeticiones deben ser mayor a 0. Se pone 10 por default");
            this.repeticiones = 10;
        } else {
            this.repeticiones = repeticiones;
        }
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos <= 0) {
            System.out.println("La duración debe ser mayor a 0. Se pone 1 minuto");
            this.duracionMinutos = 1;
        } else {
            this.duracionMinutos = duracionMinutos;
        }
    }

    // Duración total aproximada del ejercicio (cada repetición ≈ 1 min)
    public int getDuracionTotal() {
        return series * repeticiones * duracionMinutos;   // aquí se multiplica todo
    }

    // Muestra la info del ejercicio bonito
    public void mostrarInfo() {
        System.out.printf("  - %s - %d series x %d reps (%d min por serie) → Total: %d min\n",
                nombre, series, repeticiones, duracionMinutos, getDuracionTotal());
    }
}

/**
 * Clase PlanEntrenamiento
 */
class PlanEntrenamiento {
    private String nombrePlan;
    private String objetivo;
    private List<Ejercicio> ejercicios = new ArrayList<>(); // máximo 5
    private Miembro miembro;

    public PlanEntrenamiento() {
        this.nombrePlan = "Plan sin nombre";
        this.objetivo = "Sin objetivo definido";
        this.ejercicios = new ArrayList<>();
    }

    public PlanEntrenamiento(String nombrePlan, String objetivo, Miembro miembro) {
        this.nombrePlan = nombrePlan;
        this.objetivo = objetivo;
        this.miembro = miembro;
        this.ejercicios = new ArrayList<>();
    }

    public PlanEntrenamiento(PlanEntrenamiento otro) {
        this.nombrePlan = otro.nombrePlan;
        this.objetivo = otro.objetivo;
        this.miembro = otro.miembro; // referencia, no copia el miembro
        this.ejercicios = new ArrayList<>();
        for (Ejercicio e : otro.ejercicios) {
            this.ejercicios.add(new Ejercicio(e)); // copia profunda de ejercicios
        }
    }

    // Agregar ejercicio (solo si no llegamos a 5)
    public void agregarEjercicio(Ejercicio e) {
        if (ejercicios.size() >= 5) {
            System.out.println("Ya tienes 5 ejercicios. No se puede agregar más.");
            return;
        }
        ejercicios.add(new Ejercicio(e)); // copia para no compartir
        System.out.println("Ejercicio agregado: " + e.getNombre());
    }

    // Duración total del plan
    public int getDuracionTotalPlan() {
        int total = 0;
        for (Ejercicio e : ejercicios) {
            total += e.getDuracionTotal();
        }
        return total;
    }

    // Getters y setters simples
    public String getNombrePlan() { return nombrePlan; }
    public void setNombrePlan(String nombrePlan) { this.nombrePlan = nombrePlan; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public List<Ejercicio> getEjercicios() { return new ArrayList<>(ejercicios); } // copia para proteger
    public Miembro getMiembro() { return miembro; }
    public void setMiembro(Miembro miembro) { this.miembro = miembro; }

    // Mostrar el plan completo
    public void mostrarInfoPlan() {
        System.out.println("\n=== Plan: " + nombrePlan + " ===");
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Duración total aproximada: " + getDuracionTotalPlan() + " minutos");
        System.out.println("Ejercicios (" + ejercicios.size() + " de 5):");
        int i = 1;
        for (Ejercicio e : ejercicios) {
            System.out.print(i + ". ");
            e.mostrarInfo();
            i++;
        }
        if (ejercicios.isEmpty()) {
            System.out.println("  (Aún no hay ejercicios)");
        }
    }
}

/**
 * Clase Miembro (ya venía casi completa)
 */
class Miembro {
    private String nombre;
    private int edad;
    private String tipoMembresia;
    private PlanEntrenamiento plan;

    public Miembro() {
        this.nombre = "Sin nombre";
        this.edad = 0;
        this.tipoMembresia = "básica";
    }

    public Miembro(String nombre, int edad, String tipoMembresia) {
        this.nombre = nombre;
        setEdad(edad);           // usamos setter con validación
        setTipoMembresia(tipoMembresia);
    }

    public Miembro(Miembro otro) {
        this.nombre = otro.nombre;
        this.edad = otro.edad;
        this.tipoMembresia = otro.tipoMembresia;
        // NO copiamos el plan automáticamente
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) {
        if (edad < 14 || edad > 100) {
            System.out.println("Edad rara... se pone 18 por default");
            this.edad = 18;
        } else {
            this.edad = edad;
        }
    }

    public String getTipoMembresia() { return tipoMembresia; }
    public void setTipoMembresia(String tipoMembresia) {
        if (tipoMembresia.equalsIgnoreCase("básica") || tipoMembresia.equalsIgnoreCase("premium")) {
            this.tipoMembresia = tipoMembresia.toLowerCase();
        } else {
            System.out.println("Solo 'básica' o 'premium'. Se pone básica.");
            this.tipoMembresia = "básica";
        }
    }

    public PlanEntrenamiento getPlan() { return plan; }
    public void setPlan(PlanEntrenamiento plan) { this.plan = plan; }

    public boolean tienePlanActivo() {
        return plan != null;
    }

    public void mostrarInfo() {
        System.out.println("=== Datos del Miembro ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Membresía: " + tipoMembresia);
        if (plan != null) {
            System.out.println("Plan activo: " + plan.getNombrePlan());
        } else {
            System.out.println("Sin plan asignado aún");
        }
    }
}

/**
 * Clase Entrenador (ya venía casi lista)
 */
class Entrenador {
    private String nombre;
    private String especialidad;
    private String certificacion;

    public Entrenador() {
        this.nombre = "Sin nombre";
        this.especialidad = "General";
        this.certificacion = "Ninguna";
    }

    public Entrenador(String nombre, String especialidad, String certificacion) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.certificacion = certificacion;
    }

    public Entrenador(Entrenador otro) {
        this.nombre = otro.nombre;
        this.especialidad = otro.especialidad;
        this.certificacion = otro.certificacion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getCertificacion() { return certificacion; }
    public void setCertificacion(String certificacion) { this.certificacion = certificacion; }

    public void mostrarInfo() {
        System.out.println("=== Datos del Entrenador ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Certificación: " + certificacion);
    }
}

/**
 * Menú Interactivo (la parte más larga)
 */
class MenuGimnasio {
    private static Scanner sc = new Scanner(System.in);
    private static List<Miembro> miembros = new ArrayList<>();
    private static List<Ejercicio> ejerciciosCreados = new ArrayList<>(); // para reutilizar

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> crearMiembro();
                case 2 -> crearEjercicio();
                case 3 -> crearPlanEntrenamiento();
                case 4 -> crearEntrenador();
                case 5 -> System.out.println("\n¡Nos vemos en el gym!");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ GIMNASIO ===");
        System.out.println("1. Crear miembro");
        System.out.println("2. Crear ejercicio");
        System.out.println("3. Crear plan de entrenamiento");
        System.out.println("4. Crear entrenador (solo muestra)");
        System.out.println("5. Salir");
        System.out.print("→ ");
    }

    private static void crearMiembro() {
        System.out.println("\n=== CREAR MIEMBRO ===");
        String nombre = leerTexto("Nombre: ");
        int edad = leerEntero("Edad: ");
        String membresia = leerTexto("Tipo de membresía (básica/premium): ");

        Miembro m = new Miembro(nombre, edad, membresia);
        miembros.add(m);
        m.mostrarInfo();
        System.out.println("Miembro creado exitosamente.");
    }

    private static void crearEjercicio() {
        System.out.println("\n=== CREAR EJERCICIO ===");
        String nombre = leerTexto("Nombre del ejercicio: ");
        int series = leerEntero("Número de series: ");
        int reps = leerEntero("Número de repeticiones: ");
        int duracion = leerEntero("Duración por serie (min): ");

        Ejercicio e = new Ejercicio(nombre, series, reps, duracion);
        ejerciciosCreados.add(e);
        System.out.println("Ejercicio creado exitosamente.");
        e.mostrarInfo();
    }

    private static void crearPlanEntrenamiento() {
        if (miembros.isEmpty()) {
            System.out.println("Primero crea al menos un miembro");
            return;
        }

        System.out.println("\n=== CREAR PLAN DE ENTRENAMIENTO ===");
        String nombrePlan = leerTexto("Nombre del plan: ");
        String objetivo = leerTexto("Objetivo (ej. Musculación, Pérdida de peso): ");

        System.out.println("\nElige miembro para asignar el plan:");
        for (int i = 0; i < miembros.size(); i++) {
            System.out.println((i+1) + ". " + miembros.get(i).getNombre());
        }
        int idx = leerEntero("Número del miembro: ") - 1;
        if (idx < 0 || idx >= miembros.size()) {
            System.out.println("Miembro no válido. Se cancela.");
            return;
        }
        Miembro miembroSeleccionado = miembros.get(idx);

        PlanEntrenamiento plan = new PlanEntrenamiento(nombrePlan, objetivo, miembroSeleccionado);

        // Agregar hasta 5 ejercicios
        System.out.println("\n=== AGREGAR EJERCICIOS (máx 5, escribe 'fin' para terminar) ===");
        while (plan.getEjercicios().size() < 5) {
            System.out.println("\nEjercicio " + (plan.getEjercicios().size() + 1) + ":");
            String nom = leerTexto("Nombre (o 'fin' para terminar): ");
            if (nom.trim().equalsIgnoreCase("fin")) {
                break;
            }
            int s = leerEntero("Series: ");
            int r = leerEntero("Repeticiones: ");
            int d = leerEntero("Duración por serie (min): ");

            Ejercicio e = new Ejercicio(nom, s, r, d);
            plan.agregarEjercicio(e);
        }

        miembroSeleccionado.setPlan(plan);
        System.out.println("\nPlan creado y asignado exitosamente:");
        plan.mostrarInfoPlan();
    }

    private static void crearEntrenador() {
        System.out.println("\n=== CREAR ENTRENADOR (solo demo) ===");
        String nombre = leerTexto("Nombre: ");
        String esp = leerTexto("Especialidad: ");
        String cert = leerTexto("Certificación: ");

        Entrenador ent = new Entrenador(nombre, esp, cert);
        ent.mostrarInfo();
        System.out.println("Entrenador creado (pero no se guarda, solo muestra).");
    }

    // Helpers para leer con manejo básico de errores
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("¡Pon un número válido!");
            }
        }
    }
}