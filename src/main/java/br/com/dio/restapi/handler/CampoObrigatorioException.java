package br.com.dio.restapi.handler;

public class CampoObrigatorioException extends BusinessException{
    public CampoObrigatorioException(String messagem) {
        super("O campo %s é obrigatório", messagem);
    }
}
