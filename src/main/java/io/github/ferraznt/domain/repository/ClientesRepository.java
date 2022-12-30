package io.github.ferraznt.domain.repository;


import io.github.ferraznt.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

        // Caso eu queria usar o SQL Nativo
        //@Query(value = " select * from cliente c where upper(c.nome) like upper(:nome) ", nativeQuery = true)
        @Query(value = " select c from Cliente c where upper(c.nome) like upper(:nome) ")
        List<Cliente> consultarPorNomeIC(@Param("nome") String nome);

        // Exemplo de DML com @Query
        @Query(" delete from Cliente c where upper(c.nome) = upper(:nome)")
        @Modifying
        void deleteByNome(String nome);

        //Query Utilizando Query Methods
        List<Cliente> findByNomeContainingIgnoreCase(String nome);

        boolean existsByNomeContainingIgnoreCase(String nome);

        @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
        Cliente findClienteFetchPedidos(@Param("id") Integer id);

}