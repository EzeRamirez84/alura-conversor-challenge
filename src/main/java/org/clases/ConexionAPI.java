package org.clases;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ConexionAPI {
    private final String direccion;
    private String currencyFrom;
    private String currencyTo;

    public ConexionAPI(String apiKey){
        this.direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/";
        this.currencyFrom = "USD";
        this.currencyTo = "ARS";

    }

    public ConexionAPI(String apiKey, String currencyPair){
        this(apiKey);
        List<String> currencies = Arrays.stream(currencyPair.split("/")).toList();
        this.currencyFrom = currencies.get(0);
        this.currencyTo = currencies.get(1);
    }

    public String getCurrencyPair() {
        return currencyFrom + "/" + currencyTo;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
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
