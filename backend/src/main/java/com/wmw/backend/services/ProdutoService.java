package com.wmw.backend.services;

import com.wmw.backend.dto.ProdutoDTO;
import com.wmw.backend.entities.Produto;
import com.wmw.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        // Converte a lista de Entidades 'Produto' para uma lista de 'ProdutoDTO'
        return produtos.stream().map(this::mapToProdutoDTO).collect(Collectors.toList());
    }

    private ProdutoDTO mapToProdutoDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}