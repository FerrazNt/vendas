package io.github.ferraznt.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUTO_SEQ")
    @SequenceGenerator(name = "PRODUTO_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco_unitario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(BigDecimal preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco_unitario=" + preco_unitario +
                '}';
    }

}
