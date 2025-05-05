package s3;

public class Barco {
    protected String tipo;
    protected int tamaño;
    protected String orientacion;
    protected boolean[] estado_barco;
    protected int fila;
    protected int columna;

    public Barco(int tamaño,int fila,int columna,String orientacion) {
        this.tipo = "Barco";
        this.tamaño = tamaño;
        this.estado_barco = new boolean[tamaño];
        this.fila = fila;
        this.columna = columna;
        this.orientacion = orientacion;
    }
    public Barco(int tamaño){
        this.tamaño = tamaño;
        this.orientacion = orientacion;
        this.estado_barco = new boolean[tamaño];
        this.fila = fila;
        this.columna = columna;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public boolean[] getEstado_barco() {
        return estado_barco;
    }

    public void setEstado_barco(boolean[] estado_barco) {
        this.estado_barco = estado_barco;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
