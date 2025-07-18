package br.com.dougluciano.enums;

public enum ServerUserMessages {

    SERVER_STATUS_INIT("Server started on port: "),
    USER_STOP_SERVER_MESSAGE("Press Ctrl+C to stop."),
    PROPERTIES_FILE_NOT_FOUND("UNABLE TO FIND PROPERTIES FILE "),
    SERVER_START_FAIL("Falied to start the server: ");

    private String message;

    ServerUserMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
