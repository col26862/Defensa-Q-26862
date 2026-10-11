import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Mantiene los módulos en orden y administra los recursos compartidos de la misión. */
public class Mision {
    private final List<Modulo> modulos = new ArrayList<>();
    private double energiaDisponible = 50;
    private double datosEnOrbita;
    private double totalDescargado;
    private int numeroCiclo;

    public void agregarModulo(Modulo modulo) { modulos.add(modulo); }
    public List<Modulo> getModulos() { return Collections.unmodifiableList(modulos); }
    public double getEnergiaDisponible() { return energiaDisponible; }
    public double getDatosEnOrbita() { return datosEnOrbita; }
    public double getTotalDescargado() { return totalDescargado; }
    public int getNumeroCiclo() { return numeroCiclo; }
    public void consumirEnergia(double cantidad) { energiaDisponible -= cantidad; }
    public void agregarEnergia(double cantidad) { energiaDisponible += cantidad; }
    public void agregarDatosEnOrbita(double cantidad) { datosEnOrbita += cantidad; }
    public void descargarDatos(double cantidad) { datosEnOrbita -= cantidad; totalDescargado += cantidad; }

    public List<String> avanzarCiclo() {
        numeroCiclo++;
        List<String> resultados = new ArrayList<>();
        for (Modulo modulo : modulos) resultados.add(modulo.procesarCiclo(this));
        return resultados;
    }
    public Modulo buscarPorId(int id) {
        for (Modulo modulo : modulos) if (modulo.getId() == id) return modulo;
        return null;
    }
    public List<Modulo> buscarPorNombre(String texto) {
        List<Modulo> encontrados = new ArrayList<>();
        for (Modulo modulo : modulos)
            if (modulo.getNombre().toLowerCase().contains(texto.toLowerCase())) encontrados.add(modulo);
        return encontrados;
    }
    public List<Modulo> catalogoPorCosto() {
        List<Modulo> copia = new ArrayList<>(modulos);
        copia.sort(Comparator.comparingDouble(Modulo::getCostoConstruccion));
        return copia;
    }
    public List<ModuloTierra> getModulosTierra() {
        List<ModuloTierra> resultado = new ArrayList<>();
        for (Modulo modulo : modulos) if (modulo instanceof ModuloTierra) resultado.add((ModuloTierra) modulo);
        return resultado;
    }
}
