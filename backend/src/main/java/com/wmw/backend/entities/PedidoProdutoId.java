package com.wmw.backend.entities;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoProdutoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pedidoId;
    private Long produtoId;
    
    public PedidoProdutoId() {
    }

    public PedidoProdutoId(Long pedidoId, Long produtoId) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}