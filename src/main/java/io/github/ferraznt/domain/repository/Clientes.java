package io.github.ferraznt.domain.repository;


import io.github.ferraznt.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

        List<Cliente> findByNomeContainingIgnoreCase(String ch);

}
