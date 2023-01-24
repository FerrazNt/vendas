package io.github.ferraznt.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUTO_SEQ")
    @SequenceGenerator(name = "PRODUTO_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao", length = 200)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco_unitario;


}
