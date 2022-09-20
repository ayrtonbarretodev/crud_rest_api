package br.com.dio.restapi.service;

import br.com.dio.restapi.handler.CampoObrigatorioException;
import br.com.dio.restapi.model.Usuario;
import br.com.dio.restapi.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){
        if (usuario.getLogin()==null){
            throw new CampoObrigatorioException("login");
        }

        if (usuario.getPassword()==null){
            throw new CampoObrigatorioException("password");
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id){
            usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username){
        return usuarioRepository.findByLogin(username);
    }
}
