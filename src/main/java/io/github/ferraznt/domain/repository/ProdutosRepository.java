package io.github.ferraznt.domain.repository;

import io.github.ferraznt.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {


}
