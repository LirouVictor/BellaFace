package com.wmw.backend.dto;

public class ItemPedidoRequestDTO {
    private Long produtoId;
    private int quantidade;

    public ItemPedidoRequestDTO() {
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}