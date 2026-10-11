/** Panel solar que produce energía mientras permanece activo. */
public class ModuloEnergia extends Modulo {
    private final double energiaPorCiclo;
    private int ciclosOperados;
    private double totalGenerado;

    public ModuloEnergia(int id, String nombre, int salud, boolean activo, double costo, double energiaPorCiclo) {
        super(id, nombre, salud, activo, costo);
        this.energiaPorCiclo = energiaPorCiclo;
    }
    @Override public String procesarCiclo(Mision mision) {
        if (!isActivo()) return getNombre() + ": no genera energía porque está inactivo.";
        mision.agregarEnergia(energiaPorCiclo);
        totalGenerado += energiaPorCiclo;
        ciclosOperados++;
        return String.format("%s genera %.1f unidades de energía.", getNombre(), energiaPorCiclo);
    }
    @Override public String getTipo() { return "Energía"; }
    @Override public String detalle() {
        return String.format("Generación/ciclo: %.1f unidades | Ciclos operados: %d | Histórico generado: %.1f unidades",
                energiaPorCiclo, ciclosOperados, totalGenerado);
    }
    public double getEnergiaPorCiclo() { return energiaPorCiclo; }
    public int getCiclosOperados() { return ciclosOperados; }
    public double getTotalGenerado() { return totalGenerado; }
}
