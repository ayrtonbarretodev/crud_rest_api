package br.com.dio.restapi.dto;

import br.com.dio.restapi.model.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UsuarioDto {
    private String nome;
    private String especialidade;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getLogin();
        this.especialidade = usuario.getEspecialidade();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios){
        List<UsuarioDto> dtos = new ArrayList<>();
        for (Usuario u: usuarios) {
            dtos.add(new UsuarioDto(u));
        }
        return dtos;
    }
}
