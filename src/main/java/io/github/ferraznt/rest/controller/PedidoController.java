package io.github.ferraznt.rest.controller;


import io.github.ferraznt.domain.entity.ItemPedido;
import io.github.ferraznt.domain.entity.Pedido;
import io.github.ferraznt.rest.dto.InformacoesItemPedidoDTO;
import io.github.ferraznt.rest.dto.InformacoesPedidoDTO;
import io.github.ferraznt.rest.dto.PedidoDTO;
import io.github.ferraznt.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido Não Encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itens(converter(pedido.getItens()))
                .build();

    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                itemPedido -> InformacoesItemPedidoDTO
                    .builder().descricaoProduto(itemPedido.getProduto().getDescricao())
                    .precoUnitario(itemPedido.getProduto().getPreco_unitario())
                    .quantidade(itemPedido.getQuantidade())
                    .build()
        ).collect(Collectors.toList());
    }

}

