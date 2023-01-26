package io.github.ferraznt.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.login.obridatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{campo.senha.obridatorio}")
    private String senha;

    @Column
    private boolean admin;

}
