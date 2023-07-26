package com.udemy.curso.primeiro_projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.primeiro_projeto.model.Produto;
import com.udemy.curso.primeiro_projeto.repository.ProdutoRepository_old;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository_old produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return Lista de produtos
     */

    public List<Produto> obterTodos() {

        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto pelo seu id
     * 
     * @param id do produto que será localizado.
     * @return retorna o produto caso seja encontrado
     */

    public Optional<Produto> obterPorId(Integer id) {

        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista
     * 
     * @param produto que sera adicionado
     * @return retorna o produto que será adicionado na lista
     */

    public Produto adicionar(Produto produto) {

        return produtoRepository.adicionar(produto);

    }

    /**
     * Metodo para deletar produto por id
     * 
     * @param id do prduto a ser deletado
     */
    public void deletar(Integer id) {

        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar produto na lista
     * 
     * @param produto que sera atualizado
     * @return retorna o produto apos atualizar a lista
     */
    public Produto atualizar(Integer id, Produto produto) {

        produto.setId(id);
        return produtoRepository.atualizar(produto);

    }

}
