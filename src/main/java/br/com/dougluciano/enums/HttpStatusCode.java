package br.com.dougluciano.enums;

public enum HttpStatusCode {


    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    OK(200),
    NO_CONTENT(204),
    CREATED(201);

    private final int code;

    HttpStatusCode(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }
}
