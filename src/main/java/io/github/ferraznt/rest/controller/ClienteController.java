package io.github.ferraznt.rest.controller;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    //@Autowired
    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if(cliente.isPresent()){
            ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            return responseEntity;
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity salvar(@RequestBody Cliente cliente){
        Cliente novoCliente = clientesRepository.save(cliente);
        return ResponseEntity.ok(novoCliente);

    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if(cliente.isPresent()){
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
