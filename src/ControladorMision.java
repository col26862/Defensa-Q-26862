import java.util.ArrayList;
import java.util.List;

/** Conecta las opciones de la vista con los datos y acciones de la misión. */
public class ControladorMision {
    private final Mision mision;
    private final VistaConsola vista;
    public ControladorMision(Mision mision, VistaConsola vista) { this.mision = mision; this.vista = vista; }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            switch (vista.leerEntero()) {
                case 1: vista.mostrarModulos(mision.getModulos()); break;
                case 2: buscar(); break;
                case 3: vista.mostrarModulos(mision.catalogoPorCosto()); break;
                case 4: vista.mostrarCiclo(mision, mision.avanzarCiclo()); break;
                case 5: mostrarResumenComunicaciones(); break;
                case 0: continuar = false; vista.mostrarMensaje("Misión finalizada."); break;
                default: vista.mostrarMensaje("Opción no válida.");
            }
        }
        vista.cerrar();
    }
    private void buscar() {
        String criterio = vista.leerTexto("Buscar por ID o nombre: ");
        try {
            Modulo modulo = mision.buscarPorId(Integer.parseInt(criterio));
            if (modulo == null) vista.mostrarMensaje("No existe un módulo con ese ID.");
            else vista.mostrarModulos(java.util.Collections.singletonList(modulo));
        } catch (NumberFormatException ex) {
            vista.mostrarModulos(mision.buscarPorNombre(criterio));
        }
    }
    private void mostrarResumenComunicaciones() {
        List<ModuloTierra> estaciones = mision.getModulosTierra();
        int activos = 0;
        double capacidadActiva = 0;
        double maximo = -1;
        List<ModuloTierra> destacados = new ArrayList<>();
        double historico = 0;
        for (ModuloTierra modulo : estaciones) {
            historico += modulo.getTotalDescargado();
            if (modulo.isActivo()) { activos++; capacidadActiva += modulo.getCapacidadDescarga(); }
            if (modulo.getTotalDescargado() > maximo) { maximo = modulo.getTotalDescargado(); destacados.clear(); destacados.add(modulo); }
            else if (Double.compare(modulo.getTotalDescargado(), maximo) == 0) destacados.add(modulo);
        }
        vista.mostrarMensaje("\n--- Resumen de comunicaciones ---");
        vista.mostrarMensaje("Módulos de tierra: " + estaciones.size() + " | Activos: " + activos);
        vista.mostrarMensaje(String.format("Capacidad de descarga de los activos: %.1f MB/ciclo", capacidadActiva));
        vista.mostrarMensaje(String.format("Total histórico descargado por módulos de tierra: %.1f MB", historico));
        if (destacados.isEmpty()) vista.mostrarMensaje("No hay módulos de tierra registrados.");
        else {
            vista.mostrarMensaje("Módulo(s) con mayor cantidad histórica descargada:");
            for (ModuloTierra modulo : destacados)
                vista.mostrarMensaje(String.format("Nombre: %s | ID: %d | Estación: %s | Descargado: %.1f MB",
                        modulo.getNombre(), modulo.getId(), modulo.getEstacion(), modulo.getTotalDescargado()));
        }
    }
}
