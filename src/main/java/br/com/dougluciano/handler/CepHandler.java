package br.com.dougluciano.handler;

import br.com.dougluciano.entities.Address;
import br.com.dougluciano.enums.ErrorMessage;
import br.com.dougluciano.enums.HeadersConfiguration;
import br.com.dougluciano.enums.HttpStatusCode;
import br.com.dougluciano.services.ViaCepClient;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class CepHandler implements HttpHandler {

    private final ViaCepClient viaCepClient;
    private final Gson gson;

    public CepHandler(ViaCepClient viaCepClient, Gson gson){
        this.viaCepClient = viaCepClient;
        this.gson = gson;
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String responseBody) throws IOException{
        exchange.getResponseHeaders().set(
                HeadersConfiguration.CONTENT_TYPE.getMessage(),
                HeadersConfiguration.APPLICATION_JSON.getMessage()
                + "; "
                + HeadersConfiguration.CHARSET_UTF8.getMessage()
        );
        exchange.sendResponseHeaders(statusCode, responseBody.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()){
            os.write(responseBody.getBytes());
        }
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Configuração do CORS
        exchange.getResponseHeaders().add(
                HeadersConfiguration.ACCESS_CONTROL_ALLOW_ORIGIN.getMessage()
                , "*");
        exchange.getResponseHeaders().add(HeadersConfiguration.ACESSS_CONTROL_ALLOW_METHODS.getMessage()
                , HeadersConfiguration.GET
                        + ", "
                        + HeadersConfiguration.OPTIONS.getMessage());
        exchange.getResponseHeaders().add(HeadersConfiguration.ACCESS_CONTROL_ALLOW_HEADERS.getMessage()
                , HeadersConfiguration.CONTENT_TYPE_AUTHORIZATION.getMessage());

        if (exchange.getRequestMethod().equalsIgnoreCase(HeadersConfiguration.OPTIONS.getMessage())){
            exchange.sendResponseHeaders(HttpStatusCode.NO_CONTENT.getCode(), -1);
            return;
        }

        try {
            String requestPath = exchange.getRequestURI().getPath();
            String cep = requestPath.substring(requestPath.lastIndexOf('/') +1);

            Optional<Address> address = this.viaCepClient.findByCep(cep);

            if(address.isPresent()){
                String jsonResponse = this.gson.toJson(address.get());
                sendResponse(exchange, HttpStatusCode.OK.getCode(), jsonResponse);
            } else {
                String errorMessage = "{\"error\": \"" + ErrorMessage.CEP_NOT_FOUND + "\"}";
                sendResponse(exchange, HttpStatusCode.NOT_FOUND.getCode(), errorMessage);
            }
        } catch (Exception e){
            System.err.println(ErrorMessage.INTERNAL_SERVER_ERROR + e.getMessage());
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(), -1);
        } finally {
            exchange.close();
        }

    }
}
