package com.udemy.curso.primeiro_projeto.view.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.primeiro_projeto.model.Produto;
import com.udemy.curso.primeiro_projeto.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Método para retornar uma lista de produtos.
     * 
     * @return Lista de produtos.
     */
    @GetMapping
    public List<Produto> obterTodos() {
        return produtoService.obterTodos();
    }

    /**
     * Método para obter um produto pelo seu ID.
     * 
     * @param id O ID do produto a ser obtido.
     * @return O produto com o ID especificado, caso encontrado.
     */
    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable Integer id) {
        return produtoService.obterPorId(id);
    }

    /**
     * Método para adicionar um novo produto.
     * 
     * @param produto O objeto do produto a ser adicionado.
     * @return O produto adicionado.
     */
    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoService.adicionar(produto);
    }

    /**
     * Método para deletar um produto pelo seu ID.
     * 
     * @param id O ID do produto a ser deletado.
     * @return Uma mensagem indicando que o produto foi deletado com sucesso.
     */
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
        return "Produto com id " + id + " deletado com sucesso";
    }

    /**
     * Método para atualizar as informações de um produto pelo seu ID.
     * 
     * @param id O ID do produto a ser atualizado.
     * @param produto O objeto do produto com as informações atualizadas.
     * @return O produto atualizado.
     */
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }
}