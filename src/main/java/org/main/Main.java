package org.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.clases.ConexionAPI;
import org.clases.ExchangeInfo;
import org.clases.GeneradorMenu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String APIKEY = System.getenv("APIKEY");

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        ConexionAPI api = new ConexionAPI(APIKEY);
        String jsonExchange;
        GeneradorMenu menu = new GeneradorMenu();
        Scanner scanner = new Scanner(System.in);

        do {
            menu.mostrarMenu();
            menu.pedirOpcion();

            switch (menu.getOpcion()){
                case 1:
                    api.setCurrencyFrom("USD");
                    api.setCurrencyTo("ARS");
                    break;
                case 2:
                    api.setCurrencyFrom("ARS");
                    api.setCurrencyTo("USD");
                    break;
                case 3:
                    api.setCurrencyFrom("USD");
                    api.setCurrencyTo("BRL");
                    break;
                case 4:
                    api.setCurrencyFrom("BRL");
                    api.setCurrencyTo("USD");
                    break;
                case 5:
                    api.setCurrencyFrom("USD");
                    api.setCurrencyTo("COP");
                    break;
                case 6:
                    api.setCurrencyFrom("COP");
                    api.setCurrencyTo("USD");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opcion invalida: Ingrese una opcion entre 1 y 7");
            }

            if(menu.getOpcion() != 7){
                try{
                    jsonExchange = api.getJSON();
                    ExchangeInfo info = gson.fromJson(jsonExchange, ExchangeInfo.class);
                    System.out.println("Ingrese el valor que quieres convertir:");
                    double valor = scanner.nextDouble();
                    System.out.println("El valor " + valor +
                            " [" + api.getCurrencyFrom() + "] corresponde al valor final de => " +
                            valor*info.conversionRate() + " [" + api.getCurrencyTo() + "]");

                } catch (IOException | InterruptedException e) {
                    System.out.println("Error inesperado:");
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e){
                    System.out.println("Error: Se esperaba un valor numerico");
                    System.out.println(e.getMessage());
                }
            }

        }while(menu.getOpcion() != 7);

        System.out.println("Hasta luego!");


    }
}