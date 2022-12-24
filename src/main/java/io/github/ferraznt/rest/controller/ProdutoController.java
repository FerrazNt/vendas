package io.github.ferraznt.rest.controller;

import io.github.ferraznt.domain.entity.Produto;
import io.github.ferraznt.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutosRepository produtosRepository;

    public ProdutoController(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }


    // Método 1 - Listar tudo ou Filtrar por Texto
    @GetMapping
    public List<Produto> buscar(Produto produto){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );

        Example example = Example.of(produto, matcher);

        return produtosRepository.findAll(example);

    }

    // Método 2 - Gravar novo produto.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto){
        return produtosRepository.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,
                          @RequestBody Produto produto){
        produtosRepository.findById(id)
                .map(produtoExiste -> {
                   produto.setId(produtoExiste.getId());
                   produtosRepository.save(produto);
                   return produto;
                });

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        produtosRepository.findById(id)
                .map(delProduto ->{
                   produtosRepository.delete(delProduto);
                   return delProduto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO! Produto não encontrado!"));
    }

}
