package br.com.dougluciano;

import br.com.dougluciano.configuration.AppConfiguration;
import br.com.dougluciano.configuration.WebServer;
import br.com.dougluciano.enums.ServerUserMessages;
import br.com.dougluciano.handler.CepHandler;
import br.com.dougluciano.services.ViaCepClient;
import com.google.gson.Gson;

import java.io.IOException;

public class ApiServer {
    public static void main(String[] args) {
        try {
            AppConfiguration configuration = new AppConfiguration();
            int serverPort = configuration.getServerPort();
            int serverThreads = configuration.getServerThreads();

            ViaCepClient viaCepClient = new ViaCepClient();
            Gson gson = new Gson();
            CepHandler cepHandler = new CepHandler(viaCepClient, gson);

            WebServer webServer = new WebServer(serverPort, serverThreads);
            webServer.registerHandler("/api/cep/", cepHandler);

            webServer.start();
        } catch (IOException e){
            System.err.println(ServerUserMessages.SERVER_START_FAIL.getMessage() + e.getMessage());
            e.printStackTrace();
        }

    }
}