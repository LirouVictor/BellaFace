package com.wmw.backend.controllers;

import com.wmw.backend.dto.PedidoRequestDTO;
import com.wmw.backend.dto.PedidoResponseDTO;
import com.wmw.backend.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(
            @RequestParam Long clienteId,
            @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        
        PedidoResponseDTO pedidoCriado = pedidoService.criarPedido(pedidoRequestDTO, clienteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }
}