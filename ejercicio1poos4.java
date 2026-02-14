import java.util.Scanner;

public class VeterinariaNoob {

    // CLASE DUEÑO 
    static class Dueno {
        public String nombre;
        public String rfc;
        public String telefono;

        public Dueno() {
            nombre = "";
            rfc = "";
            telefono = "";
        }

        public Dueno(String n, String r, String t) {
            nombre = n;
            rfc = r;
            telefono = t;
        }

        public Dueno(Dueno otro) {
            nombre = otro.nombre;
            rfc = otro.rfc;
            telefono = otro.telefono;
        }

        public void mostrar() {
            System.out.println("Dueño: " + nombre);
        }
    }

    //CLASE MASCOTA
    static class Mascota {
        public String nombre;
        public String especie;
        public int edad;
        public double peso;
        public Dueno dueno;   // aqui va el dueño 

        public Mascota() {
            nombre = "";
            especie = "";
            edad = 0;
            peso = 0.0;
            dueno = null;
        }

        public Mascota(String n, String e, int ed, double p, Dueno d) {
            nombre = n;
            especie = e;
            edad = ed;
            peso = p;
            dueno = d;
        }

        public Mascota(Mascota otra) {
            nombre = otra.nombre;
            especie = otra.especie;
            edad = otra.edad;
            peso = otra.peso;
            if (otra.dueno != null) {
                dueno = new Dueno(otra.dueno);
            }
        }

        public void mostrar() {
            System.out.println("=== Datos de la Mascota ===");
            System.out.println("Nombre: " + nombre);
            System.out.println("Especie: " + especie);
            System.out.println("Edad: " + edad + " años");
            System.out.println("Peso: " + peso + " kg");
            if (dueno != null) {
                System.out.println("Dueño: " + dueno.nombre);
            } else {
                System.out.println("No tiene dueño registrado :(");
            }
        }
    }

    // CLASE VETERINARIO
    static class Veterinario {
        public String nombre;
        public String numeroEmpleado;
        public String especialidad;
        public int anosExperiencia;

        public Veterinario() {
            nombre = "";
            numeroEmpleado = "";
            especialidad = "";
            anosExperiencia = 0;
        }

        public Veterinario(String n, String ne, String esp, int anos) {
            nombre = n;
            numeroEmpleado = ne;
            especialidad = esp;
            anosExperiencia = anos;
        }

        public Veterinario(Veterinario otro) {
            nombre = otro.nombre;
            numeroEmpleado = otro.numeroEmpleado;
            especialidad = otro.especialidad;
            anosExperiencia = otro.anosExperiencia;
        }

        public void mostrar() {
            System.out.println("=== Datos del Veterinario ===");
            System.out.println("Nombre: " + nombre);
            System.out.println("Especialidad: " + especialidad);
            System.out.println("Experiencia: " + anosExperiencia + " años");
        }
    }

    // CLASE SERVICIO 
    static class Servicio {
        public String nombre;
        public double precio;
        public int duracionMinutos;

        public Servicio() {
            nombre = "";
            precio = 0.0;
            duracionMinutos = 0;
        }

        public Servicio(String n, double p, int d) {
            nombre = n;
            precio = p;
            duracionMinutos = d;
        }

        public Servicio(Servicio otro) {
            nombre = otro.nombre;
            precio = otro.precio;
            duracionMinutos = otro.duracionMinutos;
        }
    }

    // CLASE CONSULTA 
    static class Consulta {
        public String fecha;           
        public Veterinario veterinario;
        public Servicio servicio;
        public double costoTotal;      // por ahora solo es el precio del servicio

        public Consulta() {
            fecha = "";
            mascota = null;
            veterinario = null;
            servicio = null;
            costoTotal = 0.0;
        }

        public Consulta(String f, Mascota m, Veterinario v, Servicio s) {
            fecha = f;
            mascota = m;
            veterinario = v;
            servicio = s;
            if (s != null) {
                costoTotal = s.precio;
            }
        }

        public Consulta(Consulta otra) {
            fecha = otra.fecha;
            if (otra.mascota != null) mascota = new Mascota(otra.mascota);
            if (otra.veterinario != null) veterinario = new Veterinario(otra.veterinario);
            if (otra.servicio != null) servicio = new Servicio(otra.servicio);
            costoTotal = otra.costoTotal;
        }

        public void mostrar() {
            System.out.println("=== Consulta Realizada ===");
            System.out.println("Fecha: " + fecha);
            if (servicio != null) {
                System.out.println("Servicio: " + servicio.nombre);
            }
            System.out.printf("Costo: $%.2f\n", costoTotal);
        }
    }

    // MAIN (bien desastroso) 
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        // Datos duros 
        Dueno dueno = new Dueno("Juan Pérez", "JUPA890101", "55-1234-5678");

        Mascota max = new Mascota("Max", "Perro", 3, 15.5, dueno);

        Veterinario vet = new Veterinario("Dra. García", "VET-456", "Medicina General", 5);

        Servicio vacuna = new Servicio("Vacunación", 350.00, 20);

        Consulta consulta = new Consulta("2024-01-15", max, vet, vacuna);

        // Imprimimos como pide el ejemplo
        max.mostrar();
        vet.mostrar();
        consulta.mostrar();

        leer.close(); 
    }
}
