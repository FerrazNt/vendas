package io.github.ferraznt;

import io.github.ferraznt.domain.entity.Cliente;
import io.github.ferraznt.domain.entity.Pedido;
import io.github.ferraznt.domain.repository.ClientesRepository;
import io.github.ferraznt.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private PedidosRepository pedidos;

    @Bean
    public CommandLineRunner init(){
        return args -> {

//            clientes.save(new Cliente("Cesinha - The Rock"));
//            clientes.save(new Cliente("Otalino Ou Ta Cholano"));
//            clientes.save(new Cliente("Milsão From The Galaxies"));
//            clientes.save(new Cliente("Vevé, El Perigote De Las Chicas"));
//            clientes.save(new Cliente("Hiago Big Boss"));
//            clientes.save(new Cliente("Otávio The Master of Big Hotweels"));
//            clientes.save(new Cliente("Marlon Brando"));
//            clientes.save(new Cliente("Bruno's"));

            Cliente clienteNovo = new Cliente("Cesinha - The Rock");
            clientes.save(clienteNovo);

            Pedido pedido = new Pedido();
            pedido.setCliente(clienteNovo);
            pedido.setDataPedido(LocalDateTime.now());
            pedido.setTotal(BigDecimal.valueOf(100000.52));

            pedidos.save(pedido);

//            Cliente clComPeido = clientes.findClienteFetchPedidos(clienteNovo.getId());
//            System.out.println(clComPeido);
//            System.out.println(clComPeido.getPedidos());

            pedidos.findByCliente(clienteNovo).forEach(System.out::println);

            // Listando todos os Clientes
//            List<Cliente> todosOsClientes = clientes.findAll();
//            todosOsClientes.forEach(System.out::println);


//            boolean existe = clientes.existsByNomeContainingIgnoreCase("mil");
//            System.out.println("Existe um cliente que possui 'mil' em seu nome? R: "+existe);
//
//            boolean existe2 = clientes.existsByNomeContainingIgnoreCase("greg");
//            System.out.println("Existe um cliente que possui 'greg' em seu nome? R: "+existe2);
//
//            List<Cliente> listarPorNome = clientes.findByNomeContainingIgnoreCase("ch");
//            List<Cliente> listarPorNome = clientes.consultarPorNomeIC("%"+"ch"+"%");
//
//            System.out.println("Listando por Nome:");
//            listarPorNome.forEach(System.out::println);
//
//            System.out.println("["+ LocalDateTime.now() +"] === DELETANDO CLIENTES ===");
//            if (listarPorNome.isEmpty()){
//                System.out.println("Nenhum Cliente Encontrado!");
//            }else{
//              for (Cliente cliente : listarPorNome) {
//                  System.out.println("Deletando o Cliente: "+cliente.getId()+" - "+cliente.getNome());
//                  clientes.delete(cliente);
//              }
//            }
        };
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){

        List<Cliente> todosOsClientes = clientes.findAll();
        return todosOsClientes;

    }

    @PostMapping("/cliente")
    public Cliente salvarClientes(@RequestBody Cliente cliente){

        System.out.println("O Cliente que você está tentando salvar é: "+cliente.getNome());
        clientes.save(cliente);
        return cliente;

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
