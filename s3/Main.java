package s3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Jugador j1 = new Jugador("SUSPUTOSMUERTOS");
        Jugador j2 = new Jugador("SPURSITO");
        j1.tb.colocarBarco();
    }
}