package br.com.dougluciano.services;

import br.com.dougluciano.entities.Address;
import br.com.dougluciano.enums.ErrorMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

/**
 * Author: Douglas O. Luciano
 */
public class ViaCepClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private static final String VIA_CEP_API_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Optional<Address> findByCep(String cep){

        String normalizedCep = cep.replaceAll("\\D", "");

        String url = VIA_CEP_API_URL.replace("{cep}", normalizedCep);

        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try{
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println(ErrorMessage.API_STATUS_CODE_ERROR.getMessage() + response.statusCode());
                return Optional.empty();
            }

            String jsonBody = response.body();

            if (jsonBody.contains("\"erro\": true")){
                return Optional.empty();
            }

            Address address = gson.fromJson(jsonBody, Address.class);
            return Optional.of(address);
        } catch (IOException | InterruptedException e){
            System.err.println(ErrorMessage.REQUEST_ERROR.getMessage() + e.getMessage());
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }
}
