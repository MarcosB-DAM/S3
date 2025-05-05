package s3;

public class Jugador {
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    GestorBarcos gestorBarcos = new GestorBarcos();
    TableroBarcos tb = new TableroBarcos();

}
