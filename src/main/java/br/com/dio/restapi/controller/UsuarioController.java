package br.com.dio.restapi.controller;

import br.com.dio.restapi.dto.UsuarioDto;
import br.com.dio.restapi.model.Usuario;
import br.com.dio.restapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> usuarios (){
        List<UsuarioDto> usuariosDto = UsuarioDto.converter(usuarioService.findAll());
        return ResponseEntity.ok().body(usuariosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario>cadastrar(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
    }

    @GetMapping("/{login}")
    public ResponseEntity<UsuarioDto> buscarPorLogin(@PathVariable(value = "login") String login){
        Optional<Usuario> user =  usuarioService.findByUsername(login);
        if (!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(user.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> remover (@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (!usuario.isPresent()){
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario>atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario){

        Optional<Usuario> usuarioOptional = usuarioService.findById(id);


        if (!usuarioOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Usuario user = usuarioOptional.get();

        user.setId(usuario.getId());
        user.setLogin(usuario.getLogin());
        user.setPassword(usuario.getPassword());
        user.setEspecialidade(usuario.getEspecialidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(user));
    }
}
