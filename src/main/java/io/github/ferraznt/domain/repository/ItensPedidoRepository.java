package io.github.ferraznt.domain.repository;

import io.github.ferraznt.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
