package org.clases;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAPI {
    private String apiKey;
    private String direccion;
    private String currencyPair;

    public ConexionAPI(String apiKey){
        this.apiKey = apiKey;
        this.direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/";
        this.currencyPair = "USD/ARS";

    }

    public ConexionAPI(String apiKey, String currencyPair){
        this(apiKey);
        this.currencyPair = currencyPair;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public String getJSON() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion+this.getCurrencyPair()))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
