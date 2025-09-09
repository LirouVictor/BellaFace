package com.wmw.backend.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long pedidoId;
    private String nomeCliente;
    private LocalDateTime dataCriacao;
    private String comentarios;
    private List<ItemPedidoResponseDTO> itens;
    private BigDecimal totalPedido;
    
    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long pedidoId, String nomeCliente, LocalDateTime dataCriacao, String comentarios,
            List<ItemPedidoResponseDTO> itens, BigDecimal totalPedido) {
        this.pedidoId = pedidoId;
        this.nomeCliente = nomeCliente;
        this.dataCriacao = dataCriacao;
        this.comentarios = comentarios;
        this.itens = itens;
        this.totalPedido = totalPedido;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<ItemPedidoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponseDTO> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }
}