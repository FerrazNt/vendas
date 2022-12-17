package io.github.ferraznt;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    private Clientes clientes;

    @Bean
    public CommandLineRunner init(){
        return args -> {
            clientes.salvar(new Cliente("Cesinha - The Rock"));
            clientes.salvar(new Cliente("Otalino Ou Ta Cholano"));
            clientes.salvar(new Cliente("Milsão From The Galaxies"));
            clientes.salvar(new Cliente("Vevé, El Perigote De Las Chicas"));
            clientes.salvar(new Cliente("Hiago Big Boss"));
            clientes.salvar(new Cliente("Otávio The Master of Big Hotweels"));
            clientes.salvar(new Cliente("Marlon Brando"));
            clientes.salvar(new Cliente("Bruno's"));


            List<Cliente> todosOsClientes = clientes.buscarTodos();
            System.out.println("["+ LocalDateTime.now() +"] === IMPRIMINDO LISTA DE CLIENTES DA GREGSYSTEM! ===");
            todosOsClientes.forEach(System.out::println);
            System.out.println("["+ LocalDateTime.now() +"] === FIM DA LISTA ===");

            List<Cliente> listarPorNome = clientes.buscarPorNome("ch");

            System.out.println("Listando por Nome:");
            listarPorNome.forEach(System.out::println);


            System.out.println("["+ LocalDateTime.now() +"] === DELETANDO CLIENTES ===");
            if (listarPorNome.isEmpty()){
                System.out.println("Nenhum Cliente Encontrado!");
            }else{
              for (Cliente cliente : listarPorNome) {
                  System.out.println("Deletando o CLinente: "+cliente.getId()+" - "+cliente.getNome());
                  clientes.delete(cliente);
              }
            }
        };
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){

        List<Cliente> todosOsClientes = clientes.buscarTodos();
        return todosOsClientes;

    }

    @PostMapping("/cliente")
    public Cliente salvarClientes(@RequestBody Cliente cliente){

        System.out.println("O Cliente que você está tentando salvar é: "+cliente.getNome());
        clientes.salvar(cliente);

        return cliente;

    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
