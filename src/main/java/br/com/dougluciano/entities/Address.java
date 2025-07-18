package br.com.dougluciano.entities;

import lombok.Data;

/**
 * Author: Douglas O. Luciano
 */
@Data
public class Address {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private Long codigoIbge;
    private Integer gia;
    private Integer ddd;
    private Integer siafi;

}
