package s3;

public class Jugador {
    String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    TableroBarcos tb = new TableroBarcos();
}
