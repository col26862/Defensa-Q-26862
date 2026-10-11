import java.util.List;
import java.util.Scanner;

/** Presenta opciones e información al operador y recoge sus respuestas. */
public class VistaConsola {
    private final Scanner entrada = new Scanner(System.in);
    public void mostrarMenu() {
        System.out.println("\n=== DEFENSA DE QTZ2 ===");
        System.out.println("1. Listar módulos\n2. Buscar por ID o nombre\n3. Catálogo por costo\n4. Avanzar ciclo\n5. Resumen de comunicaciones\n0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    public int leerEntero() {
        while (!entrada.hasNextInt()) { entrada.nextLine(); System.out.print("Ingrese un número válido: "); }
        int valor = entrada.nextInt(); entrada.nextLine(); return valor;
    }
    public String leerTexto(String mensaje) { System.out.print(mensaje); return entrada.nextLine().trim(); }
    public void mostrarModulos(List<Modulo> modulos) {
        if (modulos.isEmpty()) { System.out.println("No se encontraron módulos."); return; }
        for (Modulo modulo : modulos) System.out.println(modulo);
    }
    public void mostrarEstado(Mision mision) {
        System.out.printf("Recursos: energía %.1f | datos en órbita %.1f MB | datos descargados %.1f MB%n",
                mision.getEnergiaDisponible(), mision.getDatosEnOrbita(), mision.getTotalDescargado());
    }
    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
    public void mostrarCiclo(Mision mision, List<String> resultados) {
        System.out.println("\n--- Resultados del ciclo " + mision.getNumeroCiclo() + " ---");
        for (int i = 0; i < resultados.size(); i++) System.out.println((i + 1) + ". " + resultados.get(i));
        mostrarEstado(mision);
    }
    public void cerrar() { entrada.close(); }
}
