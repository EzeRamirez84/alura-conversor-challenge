package org.clases;
import java.util.Scanner;

public class GeneradorMenu {
    private int opcion;
    private Scanner scanner;

    public GeneradorMenu(){
        this.opcion = 1;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(){
        System.out.println("**********************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Monedas");
        System.out.println("1) Dolar =>> Peso Argentino");
        System.out.println("2) Peso Argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real Brasileño");
        System.out.println("4) Real Brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso Colombiano");
        System.out.println("6) Peso Colombiano =>> Dólar");
        System.out.println("7) Salir");
        System.out.println("Elija una opción válida:");
        System.out.println("**********************************************************");
    }


}
