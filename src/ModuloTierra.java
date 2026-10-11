/** Estación terrestre que recibe información guardada en órbita. */
public class ModuloTierra extends Modulo {
    private final String estacion;
    private final double capacidadDescarga;
    private final double energiaPorDescarga;
    private double totalDescargado;

    public ModuloTierra(int id, String nombre, int salud, boolean activo, double costo,
                        String estacion, double capacidadDescarga, double energiaPorDescarga) {
        super(id, nombre, salud, activo, costo);
        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
    }
    @Override public String procesarCiclo(Mision mision) {
        if (!isActivo()) return getNombre() + ": no descarga porque está inactivo.";
        if (mision.getDatosEnOrbita() <= 0) return getNombre() + ": no descarga porque no hay datos pendientes en órbita.";
        if (mision.getEnergiaDisponible() < energiaPorDescarga)
            return getNombre() + ": no descarga porque no hay energía suficiente.";
        double recibidos = Math.min(capacidadDescarga, mision.getDatosEnOrbita());
        mision.consumirEnergia(energiaPorDescarga);
        mision.descargarDatos(recibidos);
        totalDescargado += recibidos;
        return String.format("%s, estación %s, descarga %.1f MB y consume %.1f unidades de energía.", getNombre(), estacion, recibidos, energiaPorDescarga);
    }
    @Override public String getTipo() { return "Tierra"; }
    @Override public String detalle() {
        return String.format("Estación: %s | Capacidad de descarga: %.1f MB/ciclo | Energía/descarga: %.1f | Histórico descargado: %.1f MB",
                estacion, capacidadDescarga, energiaPorDescarga, totalDescargado);
    }
    public String getEstacion() { return estacion; }
    public double getCapacidadDescarga() { return capacidadDescarga; }
    public double getEnergiaPorDescarga() { return energiaPorDescarga; }
    public double getTotalDescargado() { return totalDescargado; }
}
