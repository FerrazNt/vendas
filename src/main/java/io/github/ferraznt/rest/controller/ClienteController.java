package io.github.ferraznt.rest.controller;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.repository.ClientesRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    //@Autowired
    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente Encontrado."),
            @ApiResponse(code = 404, message = "Cliente Não Encontrado para o ID informado.")
    })
    public Cliente getClienteById(@ApiParam(name = "id"
                                          , value = "ID do Usuário"
                                          , example = "1"
                                          , required = true) @PathVariable Integer id){
        return  clientesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO! Cliente Não Encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um NOVO Cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente Salvo com Sucesso."),
            @ApiResponse(code = 400, message = "Erro de Validação.")
    })
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return clientesRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientesRepository.findById(id)
                .map(delCliente -> {
                    clientesRepository.delete(delCliente);
                    return delCliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO! Cliente Não Encontrado!"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // @ResponseBody Dessnecessário quando a Classe está anotada com RestController
    public void atualizar(@PathVariable Integer id,
                          @RequestBody @Valid Cliente cliente){
       clientesRepository.findById(id)
               .map(clienteExiste -> {
                   cliente.setId(clienteExiste.getId());
                   clientesRepository.save(cliente);
                   return cliente;
               })
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO! Cliente Não Encontrado!"));
    }

    @GetMapping
    public List<Cliente> buscar(Cliente cliente){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );

        Example example = Example.of(cliente, matcher);

        return clientesRepository.findAll(example);

    }

    @GetMapping("/json")
    public List<Cliente> buscarJson(@RequestBody Cliente cliente){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );

        Example example = Example.of(cliente, matcher);

        return clientesRepository.findAll(example);


    }

}

