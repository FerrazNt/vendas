package io.github.ferraznt.domain.repository;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    /*Optional<Pedido> findById(Integer id);*/

    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(Integer id);

}
