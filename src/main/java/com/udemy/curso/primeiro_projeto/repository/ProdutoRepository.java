package com.udemy.curso.primeiro_projeto.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.udemy.curso.primeiro_projeto.model.Produto;

import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();

    private Integer ultimoId;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return Lista de produtos
     */
    public List<Produto> obterTodos() {

        return produtos;
    }

    /**
     * Metodo que retorna o produto pelo seu id
     * 
     * @param id do produto que será localizado.
     * @return retorna o produto caso seja encontrado
     */

    public Optional<Produto> obterPorId(Integer id) {

        return produtos.stream().filter(produtos -> produtos.getId() == id).findFirst();
    }

    /**
     * Metodo para adicionar produto na lista
     * 
     * @param produto que sera adicionado
     * @return retorna o produto que será adicionado na lista
     */

    public Produto adicionar(Produto produto) {

        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;

    }

    /**
     * Metodo para deletar produto por id
     * 
     * @param id do prduto a ser deletado
     */
    public void deletar(Integer id) {

        produtos.removeIf(produto -> produto.getId() == id);

    }

    /**
     * Metodo para atualizar produto na lista
     * @param produto que sera atualizado
     * @return retorna o produto apos atualizar a lista
     */
    public Produto atualizar(Produto produto) {

        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if (produtoEncontrado.isEmpty()) {

            throw new InputMismatchException("Produto não encontrado");

        }

        deletar(produto.getId());
        produtos.add(produto);

        return produto;
    }

}
