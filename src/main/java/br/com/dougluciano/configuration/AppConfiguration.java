package br.com.dougluciano.configuration;

import br.com.dougluciano.enums.ServerUserMessages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfiguration {

    private static final String PROPERTIES_FILE_NAME = "application.properties";

    private final Properties properties;

    public AppConfiguration(){

        this.properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)){
            if (input == null){
                System.err.println(
                        ServerUserMessages.PROPERTIES_FILE_NOT_FOUND.getMessage()
                                + PROPERTIES_FILE_NAME);
                return;
            }
            properties.load(input);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Retorna a porta do servidor definida no arquivo de configuração.
     * Se não for encontrada, retorna uma porta padrão (8080).
     * @return A porta do servidor.
     */
    public int getServerPort() {
        String portStr = properties.getProperty("server.port", "8080");
        return Integer.parseInt(portStr);
    }

    /**
     * Retorna o número de threads do servidor definido no arquivo de configuração.
     * Se não for encontrado, retorna um valor padrão (10).
     * @return O número de threads.
     */
    public int getServerThreads() {
        String threadsStr = properties.getProperty("server.threads", "10");
        return Integer.parseInt(threadsStr);
    }

}
