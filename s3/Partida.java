//package s3;
//
//import java.util.Scanner;
//
//public class Partida {
//    public Partida() {
//    }
//    public void jugarPartida() {
//        Scanner sc = new Scanner(System.in);
//        Jugador jugador1 = new Jugador("Jugador 1");
//        Jugador jugador2 = new Jugador("Jugador 2");
//        GestorBarcos gestorBarcos = new GestorBarcos();
//
//        jugador1.gestorBarcos.colocarBarco();
//        jugador1.gestorBarcos.colocarSubmarino();
//        jugador2.gestorBarcos.colocarBarco();
//        jugador2.gestorBarcos.colocarSubmarino();
//
//        boolean juegoActivo = true;
//        while (juegoActivo) {
//            // Turno jugador 1
//            System.out.println("Turno de " + jugador1.getNombre());
//            jugador1.realizarAtaque(jugador2);
//            if (jugador2.haPerdido()) {
//                System.out.println(jugador1.getNombre() + " ha ganado!");
//                break;
//            }
//
//            // Turno jugador 2
//            System.out.println("Turno de " + jugador2.getNombre());
//            jugador2.realizarAtaque(jugador1);
//            if (jugador1.haPerdido()) {
//                System.out.println(jugador2.getNombre() + " ha ganado!");
//                break;
//            }
//        }
//    }
//}
