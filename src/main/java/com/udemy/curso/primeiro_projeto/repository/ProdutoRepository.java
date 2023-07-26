package com.udemy.curso.primeiro_projeto.repository;

import com.udemy.curso.primeiro_projeto.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
