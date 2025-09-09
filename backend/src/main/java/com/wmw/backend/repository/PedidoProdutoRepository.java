package com.wmw.backend.repository;

import com.wmw.backend.entities.PedidoProduto;
import com.wmw.backend.entities.PedidoProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoId> {
    
}