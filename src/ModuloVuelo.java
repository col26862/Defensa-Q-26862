/** Cámara o sensor que recolecta información científica en órbita. */
public class ModuloVuelo extends Modulo {
    private final String tipoInstrumento;
    private final double datosPorCiclo;
    private final double consumoPorCiclo;
    private int ciclosOperados;
    private double totalRecolectado;

    public ModuloVuelo(int id, String nombre, int salud, boolean activo, double costo,
                       String tipoInstrumento, double datosPorCiclo, double consumoPorCiclo) {
        super(id, nombre, salud, activo, costo);
        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoPorCiclo = consumoPorCiclo;
    }
    @Override public String procesarCiclo(Mision mision) {
        if (!isActivo()) return getNombre() + ": no recolecta porque está inactivo.";
        if (mision.getEnergiaDisponible() < consumoPorCiclo)
            return getNombre() + ": no recolecta porque no hay energía suficiente.";
        mision.consumirEnergia(consumoPorCiclo);
        mision.agregarDatosEnOrbita(datosPorCiclo);
        totalRecolectado += datosPorCiclo;
        ciclosOperados++;
        return String.format("%s recolecta %.1f MB y consume %.1f unidades de energía.", getNombre(), datosPorCiclo, consumoPorCiclo);
    }
    @Override public String getTipo() { return "Vuelo"; }
    @Override public String detalle() {
        return String.format("Instrumento: %s | Datos/ciclo: %.1f MB | Consumo/ciclo: %.1f | Ciclos operados: %d | Histórico recolectado: %.1f MB",
                tipoInstrumento, datosPorCiclo, consumoPorCiclo, ciclosOperados, totalRecolectado);
    }
    public String getTipoInstrumento() { return tipoInstrumento; }
    public double getDatosPorCiclo() { return datosPorCiclo; }
    public double getConsumoPorCiclo() { return consumoPorCiclo; }
    public int getCiclosOperados() { return ciclosOperados; }
    public double getTotalRecolectado() { return totalRecolectado; }
}
