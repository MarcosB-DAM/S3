package s3;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TableroBarcos {
    Scanner teclado = new Scanner(System.in);
    public ArrayList<Barco> barcos;
    public String[][] tablero_barcos;
    public String[][] tablero_barcos_juego;
    private String[] colores = {
            "\u001B[31m", // Rojo
            "\u001B[32m", // Verde
            "\u001B[33m", // Amarillo
            "\u001B[34m", // Azul
            "\u001B[35m", // Magenta
            "\u001B[36m", // Cyan
            "\u001B[91m", // Rojo claro
            "\u001B[92m", // Verde claro
            "\u001B[93m", // Amarillo claro
            "\u001B[94m"  // Azul claro
    };
    private String reset = "\u001B[0m";
    private boolean[][] posiciones = new boolean[9][9];

    /**
     * Constructor de un tablero, se inicializarán los barcos y el propio tablero
     */
    public TableroBarcos() {
        this.barcos = new ArrayList<>();
        this.tablero_barcos = new String[9][9];
        this.tablero_barcos_juego = new String[9][9];

        //Inicializar Tablero
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0) {
                    tablero_barcos[i][j] = String.valueOf(j);
                    tablero_barcos[i][0] = " ";
                } else {
                    tablero_barcos[i][j] = "~";
                }
                if (j == 0) {
                    tablero_barcos[i][j] = String.valueOf(i);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero_barcos_juego[i][j]=tablero_barcos[i][j];
            }
        }


        //Inicializar Barcos
        Barco b1 = new Barco(1);
        barcos.add(b1);
        for (int i = 1; i < 5; i++) {
            Barco b2_i = new Barco(2);
            barcos.add(b2_i);
        }
        for (int i = 1; i < 4; i++) {
            Barco b3_i = new Barco(3);
            barcos.add(b3_i);
        }
        for (int i = 1; i < 3; i++) {
            Barco b5_i = new Barco(5);
            barcos.add(b5_i);
        }
    }

    /**
     * Muestra el tablero a la hora de colocar los barcos (se ve la posición de los barcos)
     */
    public void getTablero_barcos() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tablero_barcos[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    /**
     * Mustra el tablero a lo largo de una partida (no se ve la posición de los barcos)
     */
    public void getTablero_barcos_juego() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tablero_barcos_juego[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    /**
     *
     * @return Devuelve si el barco se ha podido colocar exitosamente o no (siempre se colocarán correctamente)
     * @throws InputMismatchException Excepción que controla que no puedas poner una letra ni palabra a la hora de indicar la fila y columna donde se colocará un barco
     */
    public boolean colocarBarco() throws InputMismatchException {

        int fila = 0;
        int columna = 0;
        int tamaño = 0;
        String orientacion;
        System.out.println("Colocando Barcos");
        getTablero_barcos();

        for (Barco barco : barcos) {
            int colorIndex = barcos.indexOf(barco) % colores.length;
            String color = colores[colorIndex];

            tamaño = barco.getTamaño();
            System.out.println("");
            System.out.println("Barco: " + barco.getTamaño() + " casillas");
            int[] filas = new int[tamaño];
            int[] columnas = new int[tamaño];

            //Orientacion
            if(barco.getTamaño()==1){
                barco.setOrientacion("H");
            }else {
                do {
                    System.out.print("Indique la orientacion: ");
                    orientacion = teclado.nextLine();

                    if (!orientacion.equalsIgnoreCase("V") && !orientacion.equalsIgnoreCase("Vertical") && !orientacion.equalsIgnoreCase("H") && !orientacion.equalsIgnoreCase("Horizontal")) {
                        System.out.println("Orientacion incorrecta");
                        System.out.println("Orientaciones diponibles: Vertical V, Horizontal H");
                    }
                    if (orientacion.equalsIgnoreCase("V") || orientacion.equalsIgnoreCase("Vertical")) {
                        barco.setOrientacion("V");
                    } else if (orientacion.equalsIgnoreCase("H") || orientacion.equalsIgnoreCase("Horizontal")) {
                        barco.setOrientacion("H");
                    }
                } while (!orientacion.equalsIgnoreCase("V") && !orientacion.equalsIgnoreCase("Vertical") && !orientacion.equalsIgnoreCase("H") && !orientacion.equalsIgnoreCase("Horizontal"));

            }
            boolean ocupado;

            do {
                ocupado = false;

                // fila
                do {
                    boolean valido = false;
                    do {
                        System.out.print("Fila (1 a 8): ");
                        try {
                            fila = teclado.nextInt();
                            valido = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Valor No Válido");
                            valido = true;
                            teclado.nextLine();
                        }
                    }while (valido==true);
                    if (fila < 1 || fila > 8) {
                        System.out.println("Fila fuera de rango.");
                    }
                } while (fila < 1 || fila > 8);

                // columna
                do {
                    boolean valido = false;
                    do {
                        System.out.print("Columna (1 a 8): ");
                        try {
                            columna = teclado.nextInt();
                            valido = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Valor No Válido");
                            valido = true;
                            teclado.nextLine();
                        }
                    }while (valido==true);
                    if (columna < 1 || columna > 8) {
                        System.out.println("Columna fuera de rango.");
                    }
                } while (columna < 1 || columna > 8);
                teclado.nextLine();

                // Ocupacion
                if (barco.getOrientacion().equals("H")) {
                    if (columna + tamaño - 1 > 8) {
                        System.out.println("No cabe horizontalmente desde esa columna.");
                        ocupado = true;
                    } else {
                        for (int i = 0; i < tamaño; i++) {
                            if (!tablero_barcos[fila][columna + i].equals("~")) {
                                System.out.println("Posición ocupada en [" + fila + "][" + (columna + i) + "]");
                                ocupado = true;
                                break;
                            }
                        }
                    }
                } else if (barco.getOrientacion().equals("V")) {
                    if (fila + tamaño - 1 > 8) {
                        System.out.println("No cabe verticalmente desde esa fila.");
                        ocupado = true;
                    } else {
                        for (int i = 0; i < tamaño; i++) {
                            if (!tablero_barcos[fila + i][columna].equals("~")) {
                                System.out.println("Posición ocupada en [" + (fila + i) + "][" + columna + "]");
                                ocupado = true;
                                break;
                            }
                        }
                    }
                }

                if (ocupado) {
                    getTablero_barcos();
                    System.out.println("Inténtalo de nuevo.");
                }

            } while (ocupado);

            // Colocar el barco en el tablero
            if (barco.getOrientacion().equals("H")) {
                for (int i = 0; i < tamaño; i++) {
                    tablero_barcos[fila][columna + i] = color + "0" + reset;
                    filas[i] = fila;
                    columnas[i] = columna + i;
                }
            } else {
                for (int i = 0; i < tamaño; i++) {
                    tablero_barcos[fila + i][columna] = color + "0" + reset;
                    filas[i] = fila + i;
                    columnas[i] = columna;
                }
            }

            barco.setFila(filas);
            barco.setColumna(columnas);
            System.out.println("Barco colocado:");
            getTablero_barcos();
        }

        return true;
    }

    public boolean recibirDisparoNormal(int fila, int columna) {
        String celda = tablero_barcos[fila][columna];
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        if (posiciones[fila][columna]){
            System.out.println("CUIDADO ya has disparado en la posición " + fila + ", " + columna);
            return false;
        }
        if (celda.contains("0")) {
            tablero_barcos[fila][columna] = rojo+"X"+reset;
            System.out.println("Impacto!!!");
            for (Barco barco : barcos) {
                int[] filas = barco.getFila();
                int[] columnas = barco.getColumna();
                for (int i = 0; i < filas.length; i++) {
                    if (filas[i] == fila && columnas[i] == columna) {
                        barco.getEstado_barco()[i] = true;
                        break;
                    }
                }
            }
            tablero_barcos_juego[fila][columna] = rojo+"X"+reset;
            posiciones[fila][columna] = true;
            return true;
        } else {
            tablero_barcos[fila][columna] = azul+"w"+reset;
            tablero_barcos_juego[fila][columna] = azul+"w"+reset;
            System.out.println("Agua...");
        }
        posiciones[fila][columna] = true;
        return true;
    }

//    public boolean recibirDisparoGordo(int fila, int columna) {
//        boolean acerto = false;
//
//        for (int i = -1; i <= 1; i++) {
//            for (int j = -1; j <= 1; j++) {
//                int f = fila + i;
//                int c = columna + j;
//
//
//                if (f >= 0 && f < tablero_barcos.length && c >= 0 && c < tablero_barcos[0].length) {
//                    String celda = tablero_barcos[f][c];
//
//                    if (celda.contains("0")) {
//                        tablero_barcos[f][c] = "X";
//                        acerto = true;
//
//
//                        for (Barco barco : barcos) {
//                            int[] filas = barco.getFila();
//                            int[] columnas = barco.getColumna();
//                            for (int k = 0; k < filas.length; k++) {
//                                if (filas[k] == f && columnas[k] == c) {
//                                    barco.getEstado_barco()[k] = true;
//                                    break;
//                                }
//                            }
//                        }
//
//                        tablero_barcos_juego[f][c] = "X";
//
//                    } else {
//
//                        if (celda.contains("~")) {
//                        tablero_barcos[f][c] = "w";
//                        tablero_barcos_juego[f][c] = "w";
//                        }
//                    }
//                }
//            }
//        }
//
//        return acerto;
//    }

    /**
     * Método mediante el que la consola coloca sus barcos aleatoriamente
     */
    public void colocarBarcoIA() {
        Random rand = new Random();

        for (Barco barco : barcos) {
            boolean colocado = false;
            int tamaño = barco.getTamaño();
            while (!colocado) {
                int fila = rand.nextInt(8) + 1;
                int columna = rand.nextInt(8) + 1;
                String orientacion = rand.nextBoolean() ? "H" : "V";
                barco.setOrientacion(orientacion);

                int[] filas = new int[tamaño];
                int[] columnas = new int[tamaño];
                boolean puedeColocar = true;

                if (orientacion.equals("H")) {
                    if (columna + tamaño - 1 > 8) continue;
                    for (int i = 0; i < tamaño; i++) {
                        if (!tablero_barcos[fila][columna + i].equals("~")) {
                            puedeColocar = false;
                            break;
                        }
                    }
                    if (puedeColocar) {
                        for (int i = 0; i < tamaño; i++) {
                            tablero_barcos[fila][columna + i] = "0";
                            filas[i] = fila;
                            columnas[i] = columna + i;
                        }
                        barco.setFila(filas);
                        barco.setColumna(columnas);
                        colocado = true;
                    }
                } else {
                    if (fila + tamaño - 1 > 8) continue;
                    for (int i = 0; i < tamaño; i++) {
                        if (!tablero_barcos[fila + i][columna].equals("~")) {
                            puedeColocar = false;
                            break;
                        }
                    }
                    if (puedeColocar) {
                        for (int i = 0; i < tamaño; i++) {
                            tablero_barcos[fila + i][columna] = "0";
                            filas[i] = fila + i;
                            columnas[i] = columna;
                        }
                        barco.setFila(filas);
                        barco.setColumna(columnas);
                        colocado = true;
                    }
                }
            }
        }
    }
}
