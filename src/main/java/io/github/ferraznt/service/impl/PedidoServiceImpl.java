package io.github.ferraznt.service.impl;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.entity.ItemPedido;
import io.github.ferraznt.domain.entity.Pedido;
import io.github.ferraznt.domain.entity.Produto;
import io.github.ferraznt.domain.repository.ClientesRepository;
import io.github.ferraznt.domain.repository.ItensPedidoRepository;
import io.github.ferraznt.domain.repository.PedidosRepository;
import io.github.ferraznt.domain.repository.ProdutosRepository;
import io.github.ferraznt.exception.RegraNegocioException;
import io.github.ferraznt.rest.dto.ItemPedidoDTO;
import io.github.ferraznt.rest.dto.PedidoDTO;
import io.github.ferraznt.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    /*
    // Substituída pela anotação dom LOMBOK (RequiredArgsConstructor) -- LEMBRAR DO FINAL nos argumentos obrigatórios
    public PedidoServiceImpl(PedidosRepository pedidos,
                             ClientesRepository clientes,
                             ProdutosRepository produtos){
        this.pedidosRepository = pedidos;
        this.clientesRepository = clientes;
        this.produtosRepository = produtos;
    }*/

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("INFO! Código de Cliente Inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItem(pedido, dto.getItens());

        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);

        pedido.setItens(itensPedido);

        return pedido;

    }

    private List<ItemPedido> converterItem(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("INFO! Pedido necessita de Itens.");
        }

        return itens
                .stream()
                .map(dto ->  {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Código de Item Inválido: "+idProduto
                                    ));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }
}
