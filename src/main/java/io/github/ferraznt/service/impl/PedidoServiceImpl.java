package io.github.ferraznt.service.impl;

import io.github.ferraznt.domain.repository.PedidosRepository;
import io.github.ferraznt.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidos){
        this.pedidosRepository = pedidos;
    }
}
