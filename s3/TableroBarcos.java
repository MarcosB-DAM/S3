package s3;

import java.util.ArrayList;
import java.util.Scanner;

public class TableroBarcos {
    private Barco[][] tableroBar;
    private ArrayList<Barco> barcos;
    public TableroBarcos() {
        tableroBar = new Barco[8][8];
        barcos = new ArrayList<Barco>();
        //inicializamos el tablero, null = agua
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                tableroBar[i][j] = null;
            }
        }
        //inicializamos los Barcos a colocar
        Barco b1 = new Barco(1);
        barcos.add(b1);
        for (int i=1; i<5; i++) {
            Barco b2i = new Barco(2);
            barcos.add(b2i);
        }
        for (int i=1; i<4; i++) {
            Barco b3i = new Barco(3);
            barcos.add(b3i);
        }
        for (int i=1; i<3; i++) {
            Barco b5i = new Barco(5);
            barcos.add(b5i);
        }
    }

    public Barco[][] getTableroBar() {
        return tableroBar;
    }
    public void setTableroBar(Barco[][] tableroBar) {
        this.tableroBar = tableroBar;
    }

    public boolean colocarBarco() {
        Scanner teclado = new Scanner(System.in);
        int fila=0;
        int columna=0;
        int tamaño=0;
        String orientacion = "";
        Barco colocado = null;
        System.out.println("Colocando Barcos: Colocarás 1 barco de 1 casilla, 4 barcos de 2 casillas, 3 barcos de 3 casillas y 2 barcos de 5 casillas");

        //El jugador colocará todos los barcos
        for (Barco barco : barcos) {

            //Se indica la fila y columna
            do {
                System.out.println("Dime la fila donde colocar un barco de tamaño " +barco.getTamaño()+ ": ");
                fila = teclado.nextInt();
                System.out.println("Ahora dime la columna:");
                columna = teclado.nextInt();
                if (fila < 0 || fila > 8 || columna < 0 || columna > 8){
                    System.out.println("ERROR: Fila o columna no válido, el máximo es 8");
                }
            } while (fila < 0 || fila > 8 || columna < 0 || columna > 8);

            //Se indica la orientación
            do {
                System.out.println("Indique la orientacion: ");
                System.out.println("V: Vertical");
                System.out.println("H: Horizontal");
                orientacion = teclado.next();

                if (!orientacion.equalsIgnoreCase("V")&&!orientacion.equalsIgnoreCase("Vertical")&&!orientacion.equalsIgnoreCase("H")&&!orientacion.equalsIgnoreCase("Horizontal")){
                    System.out.println("Orientacion incorrecta");
                    System.out.println("Orientaciones diponibles: Vertical V, Horizontal H");
                }
            }while(!orientacion.equalsIgnoreCase("V")&&!orientacion.equalsIgnoreCase("Vertical")&&!orientacion.equalsIgnoreCase("H")&&!orientacion.equalsIgnoreCase("Horizontal"));

            //Comprobar ocupacion
            int contadorFaltantes=0;
            if (orientacion.equalsIgnoreCase("H")||orientacion.equalsIgnoreCase("Horizontal")) {
                for(int i=1; i <= tamaño;i++){
                    if (columna+i-2 >7){
                        contadorFaltantes++;
                        if (tableroBar[fila-1][columna-1-contadorFaltantes].equals("0")) {
                            System.out.println("Posicion ocupada");
                            getTableroBar();
                            return false;
                        }
                    }else if (tableroBar[fila-1][columna+i-2].equals("0")) {
                        System.out.println("Posicion ocupada");
                        getTableroBar();
                        return false;
                    }
                }
            } else if (orientacion.equalsIgnoreCase("V")||orientacion.equalsIgnoreCase("Vertical")) {
                for(int i=1; i<=tamaño;i++){
                    if (fila+i-2 >7){
                        contadorFaltantes++;
                        if (tableroBar[fila-1-contadorFaltantes][columna-1].equals("0")) {
                            System.out.println("Posicion ocupada");
                            getTableroBar();
                            return false;
                        }
                    }else if (tableroBar[fila+i-2][columna-1].equals("0")) {
                        System.out.println("Posicion ocupada");
                        getTableroBar();
                        return false;
                    }
                }
            }
            getTablero_barcos();
        }

        //Colocacion
        int contadorFaltantes=0;
        if (orientacion.equalsIgnoreCase("H")||orientacion.equalsIgnoreCase("Horizontal")) {
            for(int i=1; i<=tamaño;i++){
                if (columna+i-2 >7 && colocado == null){
                    contadorFaltantes++;
                    tableroBar[fila-1][columna-1-contadorFaltantes]=colocado;
                }else{
                    colocado = tableroBar[fila-1][columna+i-2];
                    tableroBar[fila-1][columna+i-2]=colocado;
                }
            }
            System.out.println("Barco colocado");
        } else if (orientacion.equalsIgnoreCase("V")||orientacion.equalsIgnoreCase("Vertical")) {
            for(int i=1; i<=tamaño;i++){
                if (fila+i-2 >7 && colocado == null) {
                    contadorFaltantes++;
                    tableroBar[fila - 1 - contadorFaltantes][columna - 1] = colocado;
                }else{
                    colocado = tableroBar[fila-1][columna+i-2];
                    tableroBar[fila-1][columna+i-2]=colocado;
                }
            }
            System.out.println("Barco colocado");
        }
        return true;
    }

    public void getTablero_barcos() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (tableroBar[i][j] != null) {
                    System.out.print("~" + "   ");
                }
                if(tableroBar[i][j] == null){
                    System.out.print("0" + "   ");
                }
            }
            System.out.println("");
        }
    }

}
