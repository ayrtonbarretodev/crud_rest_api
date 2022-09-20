package br.com.dio.restapi.handler;

public class BusinessException extends RuntimeException{

    public BusinessException(String messagem) {
        super(messagem);
    }

    public BusinessException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }


}
