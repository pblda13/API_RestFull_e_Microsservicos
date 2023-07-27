package com.udemy.curso.primeiro_projeto.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
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
import com.udemy.curso.primeiro_projeto.shared.ProdutoDTO;
import com.udemy.curso.primeiro_projeto.view.model.ProdutoResponse;

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
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        // Obtém a lista de produtos através do serviço
        List<ProdutoDTO> produtos = produtoService.obterTodos();

        // Cria um objeto ModelMapper para mapear os objetos ProdutoDTO para
        // ProdutoResponse
        ModelMapper mapper = new ModelMapper();

        // Mapeia cada ProdutoDTO para ProdutoResponse e coleta os resultados em uma
        // lista
        List<ProdutoResponse> resposta = produtos.stream()
                .map(produtodto -> mapper.map(produtodto, ProdutoResponse.class))
                .collect(Collectors.toList());

        // Retorna a lista de produtos mapeados como resposta com o status HTTP 200 (OK)
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * Método para obter um produto pelo seu ID.
     * 
     * @param id O ID do produto a ser obtido.
     * @return O produto com o ID especificado, caso encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable Integer id) {
        try {
            // Obtém um Optional de ProdutoDTO através do serviço com base no ID fornecido
            Optional<ProdutoDTO> dto = produtoService.obterPorId(id);

            // Verifica se o Optional contém um valor (ProdutoDTO) presente
            if (dto.isPresent()) {
                // Mapeia o ProdutoDTO para ProdutoResponse usando ModelMapper
                ProdutoResponse produtoResponse = new ModelMapper().map(dto.get(), ProdutoResponse.class);

                // Retorna o ProdutoResponse como resposta com o status HTTP 200 (OK)
                return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
            } else {
                // Retorna uma resposta HTTP 204 (NO_CONTENT) caso o ProdutoDTO não seja
                // encontrado
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // Retorna uma resposta HTTP 500 (INTERNAL_SERVER_ERROR) caso ocorra uma exceção
            // durante o processamento
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
     * @param id      O ID do produto a ser atualizado.
     * @param produto O objeto do produto com as informações atualizadas.
     * @return O produto atualizado.
     */
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }
}