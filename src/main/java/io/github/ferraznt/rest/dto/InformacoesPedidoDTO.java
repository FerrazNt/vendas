package io.github.ferraznt.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String nomeCliente;
    private String cpf;
    private String dataPedido;
    private BigDecimal total;
    private List<InformacoesItemPedidoDTO> itens;


}
