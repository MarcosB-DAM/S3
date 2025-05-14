package s3;

import java.util.ArrayList;
import java.util.Scanner;


public class GestorBarcos {
    Scanner teclado = new Scanner(System.in);
    private ArrayList<Barco> barcos;
    private String[][] tablero_barcos;
    private String[][] tablero_submarino;

    public GestorBarcos() {
        this.barcos = new ArrayList<>();
        this.tablero_barcos = new String[9][9];
        this.tablero_submarino = new String[9][9];


        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {

                if (i==0){
                    tablero_barcos[i][j] = String.valueOf(j);
                    tablero_barcos[i][0] = " ";


                }else{
                    tablero_barcos[i][j] = "~";
                }
                if(j==0){
                    tablero_barcos[i][j] = String.valueOf(i);
                }


            }
        }

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {

                if (i==0){
                    tablero_submarino[i][j] = String.valueOf(j);
                    tablero_submarino[i][0] = " ";

                }else{
                    tablero_submarino[i][j] = "-";
                }
                if(j==0){
                    tablero_submarino[i][j] = String.valueOf(i);
                }

            }
        }

    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void getTablero_barcos() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                System.out.print(tablero_barcos[i][j]+"  ");
            }
            System.out.println("");
        }
    }

    public void getTablero_submarino() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                System.out.print(tablero_submarino[i][j]+"  ");
            }
            System.out.println("");
        }
    }

    public boolean colocarBarco() {
        int fila=0;
        int columna=0;
        int tamaño=0;
        String orientacion;
        System.out.println("Colocando Barco");

        //Tamaño
        do{
            System.out.println("Ingrese el tamaño del barco: ");
            tamaño = teclado.nextInt();
            if (tamaño!=1&&tamaño!=2&&tamaño!=3&&tamaño!=5){
                System.out.println("Tamaño invalido");
                System.out.println("Tamaños disponibles: 1,2,3,5");
            }
        }while (tamaño!=1&&tamaño!=2&&tamaño!=3&&tamaño!=5);
        teclado.nextLine();

        int[] filas = new int[tamaño];
        int[] columnas = new int[tamaño];

        boolean b1=false;
        boolean b2=false;
        boolean b3=false;
        boolean b5=false;
        int contadorb2=0;
        int contadorb3=0;
        int contadorb5=0;
        for(int i=0; i< barcos.size(); i++) {
            if(barcos.get(i).getTamaño()==tamaño&&barcos.get(i).getTamaño()==1){
                if (b1==true){
                    System.out.println("Ya existe el maximo de barcos de tamaño 1");
                    return false;
                }else{
                    b1=true;
                }
            }else if(barcos.get(i).getTamaño()==tamaño&&barcos.get(i).getTamaño()==2){
                contadorb2++;
                if (b2==true){
                    System.out.println("Ya existe el maximo de barcos de tamaño 2");
                    return false;
                }else if(contadorb2==4){
                    b2=true;
                }
            }else if(barcos.get(i).getTamaño()==tamaño&&barcos.get(i).getTamaño()==3) {
                contadorb3++;
                if (b3 == true) {
                    System.out.println("Ya existe el maximo de barcos de tamaño 3");
                    return false;
                } else if (contadorb3 == 3) {
                    b3 = true;
                }
            }else if(barcos.get(i).getTamaño()==tamaño&&barcos.get(i).getTamaño()==5) {
                contadorb5++;
                if (b5 == true) {
                    System.out.println("Ya existe el maximo de barcos de tamaño 5");
                    return false;
                } else if (contadorb5 == 5) {
                    b5 = true;
                }
            }
        }

        //Orientacion
        do {
            System.out.print("Indique la orientacion: ");
            orientacion = teclado.nextLine();

            if (!orientacion.equalsIgnoreCase("V")&&!orientacion.equalsIgnoreCase("Vertical")&&!orientacion.equalsIgnoreCase("H")&&!orientacion.equalsIgnoreCase("Horizontal")){
                System.out.println("Orientacion incorrecta");
                System.out.println("Orientaciones diponibles: Vertical V, Horizontal H");
            }
        }while(!orientacion.equalsIgnoreCase("V")&&!orientacion.equalsIgnoreCase("Vertical")&&!orientacion.equalsIgnoreCase("H")&&!orientacion.equalsIgnoreCase("Horizontal"));


        System.out.println("Indique fila y columna: ");
        //Fila
        do {
            System.out.print("Fila: ");
            fila = teclado.nextInt();
            if (fila<1) {
                System.out.println("No puedes poner una fila menor que 1");
            } else if (fila>8) {
                System.out.println("No puedes poner una fila mayor que 8");
            }
        }while(fila<1 || fila>8);
        teclado.nextLine();
        //Columna
        do {
            System.out.print("Columna: ");
            columna = teclado.nextInt();
            if (columna<1) {
                System.out.println("No puedes poner una columna menor que 1");
            } else if (columna>8) {
                System.out.println("No puedes poner una columna mayor que 8");
            }
        }while(columna<1 || columna>8);
        teclado.nextLine();

        //Comprobar ocupacion
        int contadorFaltantes=0;
        if (orientacion.equalsIgnoreCase("H")||orientacion.equalsIgnoreCase("Horizontal")) {
            for(int i=1; i<=tamaño;i++){
                if (columna+i >8){
                    contadorFaltantes++;
                    if (tablero_barcos[fila][columna-contadorFaltantes].equals("0")) {
                        System.out.println("Posicion ocupada");
                        getTablero_barcos();
                        return false;
                    }
                }else if (tablero_barcos[fila][columna+i].equals("0")) {
                    System.out.println("Posicion ocupada");
                    getTablero_barcos();
                    return false;
                }
            }
        } else if (orientacion.equalsIgnoreCase("V")||orientacion.equalsIgnoreCase("Vertical")) {
            for(int i=1; i<=tamaño;i++){
                if (fila+i >8){
                    contadorFaltantes++;
                    if (tablero_barcos[fila-contadorFaltantes][columna].equals("0")) {
                        System.out.println("Posicion ocupada");
                        getTablero_barcos();
                        return false;
                    }
                }else if (tablero_barcos[fila+i][columna].equals("0")) {
                    System.out.println("Posicion ocupada");
                    getTablero_barcos();
                    return false;
                }
            }
        }

        //Colocacion
        contadorFaltantes=0;
        if (orientacion.equalsIgnoreCase("H")||orientacion.equalsIgnoreCase("Horizontal")) {
            for(int i=1; i<=tamaño;i++){
                if (columna+i-1 >8){
                    contadorFaltantes++;
                    tablero_barcos[fila][columna-contadorFaltantes]="0";
                    filas[i-1]=fila;
                    columnas[i-1]=columna-contadorFaltantes;
                }else{
                    tablero_barcos[fila][columna+i-1]="0";
                    filas[i-1]=fila;
                    columnas[i-1]=columna+i-1;
                }
            }
            System.out.println("Barco colocado");
        } else if (orientacion.equalsIgnoreCase("V")||orientacion.equalsIgnoreCase("Vertical")) {
            for(int i=1; i<=tamaño;i++){
                if (fila+i-1 >8) {
                    contadorFaltantes++;
                    tablero_barcos[fila-contadorFaltantes][columna] = "0";
                    filas[i-1]=fila-contadorFaltantes;
                    columnas[i-1]=columna;
                }else{
                    tablero_barcos[fila+i-1][columna]="0";
                    filas[i-1]=fila+i-1;
                    columnas[i-1]=columna;
                }
            }
            System.out.println("Barco colocado");
        }
        Barco b =new Barco(tamaño,filas,columnas,orientacion);
        barcos.add(b);
        return true;

    }


//    public boolean comprobarEstadoBarcos(){
//        Misil misil = new Misil();
//        int[] coordenadas = misil.lanzarMisil();
//        int filaMisil = coordenadas[0];
//        int columnaMisil = coordenadas[1];
//
//        for (Barco barco : barcos) {
//            if (barco.getFila() == filaMisil && barco.getColumna() == columnaMisil) {
//                System.out.println("¡Se ha dañado un barco!");
//                return true;
//            }
//        }
//        System.out.println("Se lanzó un misil al agua");
//        return false;
//    }

    public boolean dispararMisil(){
        int fila, columna;
        System.out.println("Indique fila y columna: ");
        //Fila
        do {
            System.out.print("Fila: ");
            fila = teclado.nextInt();
            if (fila < 1) {
                System.out.println("No puedes poner una fila menor que 1");
            } else if (fila > 8) {
                System.out.println("No puedes poner una fila mayor que 8");
            }
        } while (fila < 1 || fila > 8);
        teclado.nextLine();
        //Columna
        do {
            System.out.print("Columna: ");
            columna = teclado.nextInt();
            if (columna < 1) {
                System.out.println("No puedes poner una columna menor que 1");
            } else if (columna > 8) {
                System.out.println("No puedes poner una columna mayor que 8");
            }
        } while (columna < 1 || columna > 8);
        teclado.nextLine();

        //Tocado?

        if(tablero_barcos[fila][columna].equalsIgnoreCase("0")){
            tablero_barcos[fila][columna]="X";
        }

        return true;

    }


}



