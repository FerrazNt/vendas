package io.github.ferraznt.domain.repository;


import io.github.ferraznt.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO CLIENTE (nome) values (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";

    private static String DELETE = "DELETE CLIENTE WHERE ID = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void delete(Cliente cliente){
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public List<Cliente> buscarTodos(){
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" WHERE upper(NOME) LIKE ? "),
                new Object[]{"%" + nome.toUpperCase() + "%"} ,
                getClienteRowMapper()
        );
    }

    private static RowMapper<Cliente> getClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
