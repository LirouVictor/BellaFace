package com.wmw.backend.dto;
import java.util.List;

public class PedidoRequestDTO {
    private List<ItemPedidoRequestDTO> itens;
    private String comentarios;
    
    public PedidoRequestDTO() {
    }

    public List<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}