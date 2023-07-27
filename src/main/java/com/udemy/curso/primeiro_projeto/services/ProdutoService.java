package com.udemy.curso.primeiro_projeto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.primeiro_projeto.model.Produto;
import com.udemy.curso.primeiro_projeto.model.exception.ResourceNotFoundException;
import com.udemy.curso.primeiro_projeto.repository.ProdutoRepository;
import com.udemy.curso.primeiro_projeto.repository.ProdutoRepository_old;
import com.udemy.curso.primeiro_projeto.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return Lista de produtos
     */

    public List<ProdutoDTO> obterTodos() {

        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ModelMapper().map(produtos, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto pelo seu id
     * 
     * @param id do produto que será localizado.
     * @return retorna o produto caso seja encontrado
     */

    public Optional<ProdutoDTO> obterPorId(Integer id) {

        // Obtendo optional de produto pelo ID
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se nã encontrar,lança uma exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id " + id + " não encontrado");
        }

        // Convertendo o meu optional de produto por um ProdutoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // Criando e retornando um optional de um ProdutoDTO
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista
     * 
     * @param produto que sera adicionado
     * @return retorna o produto que será adicionado na lista
     */

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {

        // Removendo o id para poder fazer o cadastro
        produtoDto.setId(null);
        // Criando um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o ProdutoDTO em um Produto

        Produto produto = mapper.map(produtoDto, Produto.class);

        // Salvar o produto do banco

        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());

        // Retornar o ProdutoDTO atualizado.
        return produtoDto;

    }

    /**
     * Metodo para deletar produto por id
     * 
     * @param id do prduto a ser deletado
     */
    public void deletar(Integer id) {

        // Verifica se o produto existe

        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não existir lança uma exception

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel deletar produto com id :" + id);
        }

        // Deleta o produto pelo id

        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar produto na lista
     * 
     * @param produto que sera atualizado
     * @return retorna o produto apos atualizar a lista
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
// Passar o id para o ProdutoDTO

produtoDto.setId(id);


// Criar um objeto de mapeamento
ModelMapper mapper = new ModelMapper();

// Converter o ProdutoDTO para um Produto

Produto produto = mapper.map(produtoDto,Produto.class);

//Atualizar o produto no banco de dados 

produtoRepository.save(produto);
//Retornar o produtoDto atualizado
return produtoDto;

      
}
}
