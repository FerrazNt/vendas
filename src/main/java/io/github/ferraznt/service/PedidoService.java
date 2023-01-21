package io.github.ferraznt.service;

import io.github.ferraznt.domain.entity.Pedido;
import io.github.ferraznt.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto );

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
