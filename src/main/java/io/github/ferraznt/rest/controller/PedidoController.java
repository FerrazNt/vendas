package io.github.ferraznt.rest.controller;


import io.github.ferraznt.domain.entity.Pedido;
import io.github.ferraznt.rest.dto.PedidoDTO;
import io.github.ferraznt.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService service){
         this.pedidoService = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Integer id){
        Optional<Pedido> pedido = pedidoService.findById(id);
        pedido.orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"Pedido não Encontrado!"));
        return pedido.get();
    }
}

