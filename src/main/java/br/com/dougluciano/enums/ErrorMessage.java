package br.com.dougluciano.enums;

/**
 * Author: Douglas O. Luciano
 */
public enum ErrorMessage {

    API_STATUS_CODE_ERROR("Error! The API returned a status code: "),
    REQUEST_ERROR("An error occured on ViaCEP request: ");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
