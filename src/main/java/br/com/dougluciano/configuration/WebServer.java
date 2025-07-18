package br.com.dougluciano.configuration;

import br.com.dougluciano.enums.ServerUserMessages;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class WebServer {

    private final HttpServer server;

    /**
     * Construtor que inicializa o servidor com as configurações fornecidas.
     * @param port A porta em que o servidor irá escutar.
     * @param threads A quantidade de threads para o pool de requisições.
     * @throws IOException Se ocorrer um erro ao criar o servidor.
     */
    public WebServer(int port, int threads) throws IOException{
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.setExecutor(Executors.newFixedThreadPool(threads));
    }

    public void registerHandler(String path, HttpHandler handler){
        this.server.createContext(path, handler);
    }

    public void start(){
        this.server.start();
        System.out.println(ServerUserMessages.SERVER_STATUS_INIT.getMessage() + this.server.getAddress());
        System.out.println(ServerUserMessages.USER_STOP_SERVER_MESSAGE.getMessage());
    }
}
