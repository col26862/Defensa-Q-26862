/** Elemento general que participa en la misión Quetzal-2. */
public abstract class Modulo {
    private final int id;
    private final String nombre;
    private int salud;
    private boolean activo;
    private final double costoConstruccion;

    public Modulo(int id, String nombre, int salud, boolean activo, double costoConstruccion) {
        this.id = id;
        this.nombre = nombre;
        this.salud = Math.max(0, Math.min(100, salud));
        this.activo = activo;
        this.costoConstruccion = Math.max(0, costoConstruccion);
    }

    public abstract String procesarCiclo(Mision mision);
    public abstract String getTipo();
    public abstract String detalle();

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public boolean isActivo() { return activo; }
    public double getCostoConstruccion() { return costoConstruccion; }
    public void setSalud(int salud) { this.salud = Math.max(0, Math.min(100, salud)); }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return String.format("[%s] ID: %d | Nombre: %s | Salud: %d%% | Estado: %s | Costo: Q%.2f | %s",
                getTipo(), id, nombre, salud, activo ? "Activo" : "Inactivo", costoConstruccion, detalle());
    }
}
