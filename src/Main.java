public class Main {
    public static void main(String[] args) {
        Mision mision = new Mision();
        cargarMision(mision);
        new ControladorMision(mision, new VistaConsola()).iniciar();
    }
    private static void cargarMision(Mision mision) {
        mision.agregarModulo(new ModuloEnergia(101, "Panel Solar A", 100, true, 1200, 20));
        mision.agregarModulo(new ModuloVuelo(201, "Cámara Quetzal", 95, true, 2500, "Cámara", 15, 8));
        mision.agregarModulo(new ModuloTierra(301, "Antena UVG", 100, true, 1800, "Campus Central", 10, 5));
        mision.agregarModulo(new ModuloEnergia(102, "Panel Solar B", 90, true, 1100, 15));
        mision.agregarModulo(new ModuloVuelo(202, "Sensor Solar", 88, true, 2200, "Sensor", 12, 7));
        mision.agregarModulo(new ModuloTierra(302, "Antena Occidente", 92, true, 1600, "Quetzaltenango", 8, 4));
        mision.agregarModulo(new ModuloVuelo(203, "Cámara Atmosférica", 80, false, 2100, "Cámara", 10, 6));
        mision.agregarModulo(new ModuloEnergia(103, "Panel Solar de Respaldo", 85, true, 950, 10));
        mision.agregarModulo(new ModuloTierra(303, "Antena Norte", 97, false, 1500, "Petén", 6, 3));
        mision.agregarModulo(new ModuloVuelo(204, "Sensor de Radiación", 90, true, 2300, "Sensor", 18, 12));
    }
}
