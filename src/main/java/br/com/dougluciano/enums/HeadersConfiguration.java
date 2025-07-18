package br.com.dougluciano.enums;

public enum HeadersConfiguration {

    ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
    ACESSS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
    ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
    CONTENT_TYPE_AUTHORIZATION("Content-Type,Authorization"),
    CONTENT_TYPE("Content-Type"),
    APPLICATION_JSON("application/json"),
    CHARSET_UTF8("charset=UTF8"),
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    OPTIONS("OPTIONS");

    private final String message;

    HeadersConfiguration(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    // Sobrescrever o método toString torna o método getMessage opcional
    @Override
    public String toString(){
        return this.message;
    }

}
