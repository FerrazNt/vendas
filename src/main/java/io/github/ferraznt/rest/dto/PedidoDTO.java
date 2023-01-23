package io.github.ferraznt.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * {
 *     "cliente":1,
 *     "total": 500,
 *     "itens":[
 *         {
 *             "produto": 1,
 *             "quantidade": 10
 *         }
 *     ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Campo CLIENTE do Pedido é Obrigatório.")
    private Integer cliente;

    @NotNull(message = "Campo TOTAL do Pedido é Obrigatório.")
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
