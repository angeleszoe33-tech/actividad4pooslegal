import java.util.Scanner; // Esto es para poder leer lo que escribe el usuario 

public class ControlEscolar {

    // CLASE MATERIA
    static class Materia {
        // Estos son los datos que guarda cada materia (los puse public para que sea más fácil)
        public String nombre;
        public String clave;
        public int creditos;
        public int horasSemanales;

        // Constructor vacío (por si alguien quiere crear una materia y luego llenarla poquito a poquito)
        public Materia() {
            nombre = "";           // cadena vacía para que no sea null
            clave = "";
            creditos = 0;
            horasSemanales = 0;
        }

        // Constructor pro: le pasas todo lo que necesita la materia
        public Materia(String nombre, String clave, int creditos, int horasSemanales) {
            this.nombre = nombre;           // "this" es para decir "mi propio nombre"
            this.clave = clave;
            this.creditos = creditos;
            this.horasSemanales = horasSemanales;
        }

        // Constructor de copia: hace una copia exacta de otra materia
        public Materia(Materia otra) {
            this.nombre = otra.nombre;      // copiamos todo lo que tiene la otra
            this.clave = otra.clave;
            this.creditos = otra.creditos;
            this.horasSemanales = otra.horasSemanales;
        }

        // Getters: sirven para que otros vean mis datos sin tocarlos directamente
        public String getNombre() { return nombre; }
        public String getClave() { return clave; }
        public int getCreditos() { return creditos; }
        public int getHorasSemanales() { return horasSemanales; }

        // Setters: sirven para cambiar mis datos después
        public void setNombre(String n) { nombre = n; }
        public void setClave(String c) { clave = c; }
        public void setCreditos(int cr) { creditos = cr; }
        public void setHorasSemanales(int h) { horasSemanales = h; }

        // Método para que la materia se presente solita (útil para imprimir)
        public void mostrarInfo() {
            System.out.println("Materia: " + nombre + ", Clave: " + clave +
                               ", Créditos: " + creditos + ", Horas semanales: " + horasSemanales);
        }
    }

    // CLASE CURSO 
    static class Curso {
        public String nombre;
        public Materia[] materias = new Materia[3]; // Siempre tiene EXACTAMENTE 3 materias, ni una más ni una menos

        // Constructor vacío
        public Curso() {
            nombre = "";
            // las materias quedan en null por ahora
        }

        // Constructor principal: le das el nombre y las 3 materias
        public Curso(String nombre, Materia m1, Materia m2, Materia m3) {
            this.nombre = nombre;
            this.materias[0] = m1;
            this.materias[1] = m2;
            this.materias[2] = m3;
        }

        // Constructor de copia (copia el curso y también hace copias nuevas de las materias)
        public Curso(Curso otro) {
            this.nombre = otro.nombre;
            // Hacemos copia profunda (nueva materia) para no compartir la misma
            this.materias[0] = new Materia(otro.materias[0]);
            this.materias[1] = new Materia(otro.materias[1]);
            this.materias[2] = new Materia(otro.materias[2]);
        }

        // Método importante: suma los créditos de las 3 materias
        public int calcularCreditosTotales() {
            int total = 0;
            // recorremos las 3 materias
            for (int i = 0; i < 3; i++) {
                if (materias[i] != null) { // por si alguna está vacía (aunque no debería)
                    total = total + materias[i].creditos;
                }
            }
            return total;
        }

        // Para que el curso se presente bien aca
        public void mostrarInfo() {
            System.out.println("Curso: " + nombre + " | Créditos totales: " + calcularCreditosTotales());
            System.out.println("Materias del curso:");
            for (Materia m : materias) {
                if (m != null) {
                    m.mostrarInfo();
                } else {
                    System.out.println("   - Materia no asignada aún");
                }
            }
        }
    }

    // CLASE PROFESOR 
    static class Profesor {
        public String nombre;
        public int numeroNomina;
        public double sueldoPorHora;
        public Materia materiaImparte; // puede ser null si todavía no le asignan materia

        // Constructor vacío
        public Profesor() {
            nombre = "";
            numeroNomina = 0;
            sueldoPorHora = 0.0;
            materiaImparte = null;
        }

        // Constructor con todo
        public Profesor(String nombre, int numeroNomina, double sueldoPorHora, Materia materia) {
            this.nombre = nombre;
            this.numeroNomina = numeroNomina;
            this.sueldoPorHora = sueldoPorHora;
            this.materiaImparte = materia;
        }

        // Constructor de copia
        public Profesor(Profesor otro) {
            this.nombre = otro.nombre;
            this.numeroNomina = otro.numeroNomina;
            this.sueldoPorHora = otro.sueldoPorHora;
            if (otro.materiaImparte != null) {
                this.materiaImparte = new Materia(otro.materiaImparte);
            } else {
                this.materiaImparte = null;
            }
        }

        // Calcula cuánto gana en la semana (sueldo por hora * horas de la materia)
        public double calcularSueldoSemanal() {
            if (materiaImparte != null) {
                return sueldoPorHora * materiaImparte.horasSemanales;
            } else {
                return 0.0; // si no tiene materia, gana 0 (pobrecito)
            }
        }

        // Muestra toda su info
        public void mostrarInfo() {
            System.out.println("Profesor: " + nombre + " | Nómina: " + numeroNomina +
                               " | Sueldo por hora: $" + sueldoPorHora);
            if (materiaImparte != null) {
                System.out.println("Imparte: ");
                materiaImparte.mostrarInfo();
                System.out.println("Sueldo semanal aproximado: $" + calcularSueldoSemanal());
            } else {
                System.out.println("Aún no tiene materia asignada :(");
            }
        }
    }

    // CLASE ALUMNO 
    static class Alumno {
        public String matricula;
        public String nombre;
        public int edad;
        public Curso cursoInscrito;

        // Constructor vacío
        public Alumno() {
            matricula = "";
            nombre = "";
            edad = 0;
            cursoInscrito = null;
        }

        // Constructor con datos
        public Alumno(String matricula, String nombre, int edad, Curso curso) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.edad = edad;
            this.cursoInscrito = curso;
        }

        // Constructor de copia
        public Alumno(Alumno otro) {
            this.matricula = otro.matricula;
            this.nombre = otro.nombre;
            this.edad = otro.edad;
            if (otro.cursoInscrito != null) {
                this.cursoInscrito = new Curso(otro.cursoInscrito);
            }
        }

        // Muestra la info del alumno
        public void mostrarInfo() {
            System.out.println("Alumno: " + nombre + " | Matrícula: " + matricula + " | Edad: " + edad);
            if (cursoInscrito != null) {
                System.out.println("Está inscrito en:");
                cursoInscrito.mostrarInfo();
            } else {
                System.out.println("No tiene curso aún");
            }
        }
    }

    // -EL MAIN (donde todo pasa) 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // la herramienta mágica para leer teclado

        System.out.println("Vamos a crear el control escolar");

        // Pedimos datos de materia 1
        System.out.println("\nMateria 1:");
        System.out.print("Nombre: ");
        String nomM1 = scanner.nextLine();
        System.out.print("Clave: ");
        String clavM1 = scanner.nextLine();
        System.out.print("Créditos: ");
        int credM1 = scanner.nextInt();
        System.out.print("Horas semanales: ");
        int horM1 = scanner.nextInt();
        scanner.nextLine(); // truco para que no se coma el enter
        Materia m1 = new Materia(nomM1, clavM1, credM1, horM1);

        // Materia 2 (casi lo mismo)
        System.out.println("\nMateria 2:");
        System.out.print("Nombre: ");
        String nomM2 = scanner.nextLine();
        System.out.print("Clave: ");
        String clavM2 = scanner.nextLine();
        System.out.print("Créditos: ");
        int credM2 = scanner.nextInt();
        System.out.print("Horas semanales: ");
        int horM2 = scanner.nextInt();
        scanner.nextLine();
        Materia m2 = new Materia(nomM2, clavM2, credM2, horM2);

        // Materia 3
        System.out.println("\nMateria 3:");
        System.out.print("Nombre: ");
        String nomM3 = scanner.nextLine();
        System.out.print("Clave: ");
        String clavM3 = scanner.nextLine();
        System.out.print("Créditos: ");
        int credM3 = scanner.nextInt();
        System.out.print("Horas semanales: ");
        int horM3 = scanner.nextInt();
        scanner.nextLine();
        Materia m3 = new Materia(nomM3, clavM3, credM3, horM3);

        // Creamos el curso
        System.out.print("\nNombre del curso: ");
        String nomCurso = scanner.nextLine();
        Curso curso = new Curso(nomCurso, m1, m2, m3);

        // Creamos un alumno
        System.out.println("\nDatos del alumno:");
        System.out.print("Matrícula: ");
        String matAl = scanner.nextLine();
        System.out.print("Nombre: ");
        String nomAl = scanner.nextLine();
        System.out.print("Edad: ");
        int edadAl = scanner.nextInt();
        scanner.nextLine();
        Alumno alumno = new Alumno(matAl, nomAl, edadAl, curso);

        // Creamos un profesor (le asignamos la materia 1 de ejemplo)
        System.out.println("\nDatos del profesor:");
        System.out.print("Nombre: ");
        String nomProf = scanner.nextLine();
        System.out.print("Número de nómina: ");
        int nomNom = scanner.nextInt();
        System.out.print("Sueldo por hora: ");
        double suelH = scanner.nextDouble();
        scanner.nextLine();
        Profesor prof = new Profesor(nomProf, nomNom, suelH, m1);

        // Mostramos todo lo que creamos
        System.out.println("\n===== RESULTADO FINAL =====");
        alumno.mostrarInfo();
        System.out.println("\n--------------------------------");
        prof.mostrarInfo();

        scanner.close(); // buena práctica cerrar el scanner 
    }
}
