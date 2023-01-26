package io.github.ferraznt.rest.controller;

import io.github.ferraznt.domain.entity.Usuario;
import io.github.ferraznt.rest.dto.UsuarioDTO;
import io.github.ferraznt.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    private final PasswordEncoder encoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvar(@RequestBody @Valid UsuarioDTO dto){

        String senhaCrypt = encoder.encode(dto.getSenha());
        dto.setSenha(senhaCrypt);
        Usuario u = usuarioService.salvar(dto);

        return u.getId();
    }

}
