package com.wmw.backend.services;

import com.wmw.backend.dto.ItemPedidoRequestDTO;
import com.wmw.backend.dto.ItemPedidoResponseDTO;
import com.wmw.backend.dto.PedidoRequestDTO;
import com.wmw.backend.dto.PedidoResponseDTO;
import com.wmw.backend.entities.*;
import com.wmw.backend.repository.ClienteRepository;
import com.wmw.backend.repository.PedidoProdutoRepository;
import com.wmw.backend.repository.PedidoRepository;
import com.wmw.backend.repository.ProdutoRepository;
import com.wmw.backend.services.exception.RegraDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto, Long clienteId) {
        // Validação de Regra de Negócio (Tempo)
        validarDiaDoPedido();

        // Busca de Dados
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteId));

        // Criação do Pedido Principal
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setComentarios(dto.getComentarios());
        pedido.setTotalPedido(BigDecimal.ZERO); // Inicializa com zero

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        List<PedidoProduto> itensSalvos = new ArrayList<>();
        BigDecimal totalCalculado = BigDecimal.ZERO;

        // Processamento dos Itens
        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + itemDto.getProdutoId()));

            BigDecimal precoTotalItem = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.getQuantidade()));
            totalCalculado = totalCalculado.add(precoTotalItem);

            PedidoProdutoId pedidoProdutoId = new PedidoProdutoId(pedidoSalvo.getId(), produto.getId());
            
            PedidoProduto item = new PedidoProduto();
            item.setId(pedidoProdutoId);
            item.setPedido(pedidoSalvo);
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setPrecoTotal(precoTotalItem);
            item.setDataCriacao(LocalDateTime.now());
            
            itensSalvos.add(pedidoProdutoRepository.save(item));
        }

        // Finalização do Pedido
        pedidoSalvo.setTotalPedido(totalCalculado);
        pedidoRepository.save(pedidoSalvo);

        // Montagem da Resposta
        return mapToPedidoResponseDTO(pedidoSalvo, cliente, itensSalvos);
    }

    private void validarDiaDoPedido() {
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        if (hoje == DayOfWeek.SATURDAY || hoje == DayOfWeek.SUNDAY) {
            throw new RegraDeNegocioException("Não é permitido criar pedidos nos finais de semana.");
        }
    }

    private PedidoResponseDTO mapToPedidoResponseDTO(Pedido pedido, Cliente cliente, List<PedidoProduto> itens) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setPedidoId(pedido.getId());
        response.setNomeCliente(cliente.getNome());
        response.setDataCriacao(pedido.getDataCriacao());
        response.setComentarios(pedido.getComentarios());
        response.setTotalPedido(pedido.getTotalPedido());

        List<ItemPedidoResponseDTO> itensResponse = new ArrayList<>();
        for (PedidoProduto item : itens) {
            ItemPedidoResponseDTO itemDto = new ItemPedidoResponseDTO();
            itemDto.setProdutoId(item.getProduto().getId());
            itemDto.setNomeProduto(item.getProduto().getNome());
            itemDto.setQuantidade(item.getQuantidade());
            itemDto.setPrecoUnitario(item.getPrecoUnitario());
            itemDto.setPrecoTotal(item.getPrecoTotal());
            itensResponse.add(itemDto);
        }
        response.setItens(itensResponse);

        return response;
    }
}