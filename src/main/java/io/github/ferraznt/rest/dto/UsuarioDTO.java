package io.github.ferraznt.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotEmpty(message = "{campo.login.obridatorio}")
    private String login;

    @NotEmpty(message = "{campo.senha.obridatorio}")
    private String senha;

    private boolean admin;
}
