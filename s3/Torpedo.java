package s3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Torpedo {
    private int fila;
    private int columna;
    private int tamañoTorpedo;

    String rojo = "\u001B[31m";
    String verde = "\u001B[32m";
    String azul = "\u001B[34m";
    String reset = "\u001B[0m";

    public Torpedo(int fila, int columna, int tamaño) {
        this.fila = fila;
        this.columna = columna;
        this.tamañoTorpedo = tamaño;
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

    public int getTamañoTorpedo() {
        return tamañoTorpedo;
    }
    public void setTamañoTorpedo(int tamañoTorpedo) {
        this.tamañoTorpedo = tamañoTorpedo;
    }

    public boolean lanzarTorpedo(Scanner teclado) {
        GestorBarcos gs = new GestorBarcos();
        int opcion = 0;
        int confirmFila, confirmColumna;
        int[][] tablero = new int[fila][columna];
        System.out.println("==============");
        System.out.println("Lanzar torpedo");
        System.out.println("==============");
        do {
            try {
                System.out.println("El torpedo cubre toda una fila o columna. ¿Quieres utilizar un torpedo para una fila (1) o una comumna (2)?");
                opcion = teclado.nextInt();
                if (opcion != 1 && opcion != 2) {
                    System.out.println(rojo + "Error: Indica si quieres atacar una fila(1) o una comumna (2)" + reset);
                }
            }
            catch (InputMismatchException e) {
                System.out.println(rojo + "Error: Indica si quieres atacar una fila (1) o una columna (2)" + reset);
            }
        } while(opcion != 1 && opcion != 2);
        for (Submarino s : gs.getSubmarinos()) {
            if (tamañoTorpedo == 2) {
                System.out.println("Se disparará un torpedo a la una fila");
                System.out.println("Indica a qué fila quieres tirar el torpedo: ");
                fila = teclado.nextInt();
                System.out.println("¿Estás seguro que quieres lanzar el torpedo en la fila " + fila + "?");
                confirmFila = teclado.nextInt();
                for (boolean estado : s.estado_barco){
                    if (s.fila == fila){
                        estado = false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Se disparará un torpedo a la una columna");
                System.out.println("Indica a qué columna quieres tirar el torpedo: ");
                columna = teclado.nextInt();
                System.out.println("¿Estás seguro que quieres lanzar el torpedo en la columna " + columna + "?");
                confirmColumna = teclado.nextInt();
                for (int i = 0; i < s.estado_barco.length; i++) {
                    if (s.getFila() == fila){
                        s.estado_barco[i] = false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}