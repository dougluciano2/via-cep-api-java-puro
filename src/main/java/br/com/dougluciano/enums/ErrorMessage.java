package br.com.dougluciano.enums;

/**
 * Author: Douglas O. Luciano
 */
public enum ErrorMessage {

    API_STATUS_CODE_ERROR("Error! The API returned a status code: "),
    REQUEST_ERROR("An error occurred on ViaCEP request: "),
    CEP_NOT_FOUND("CEP not found or invalid"),
    INTERNAL_SERVER_ERROR("Internal Servicer Error: ");

    private final String message;

    ErrorMessage(String message){
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
