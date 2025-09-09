package com.wmw.backend.controllers.exception;

import com.wmw.backend.services.exception.AutenticacaoException;
import com.wmw.backend.services.exception.RegraDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    // Trata erros de regras de negócio (ex: pedido no fds)
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<StandardError> regraDeNegocio(RegraDeNegocioException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        StandardError err = new StandardError(Instant.now(), status.value(), "Erro de Negócio", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // Trata erros quando um recurso não é encontrado (ex: clienteId ou produtoId não existe)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontrada(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        StandardError err = new StandardError(Instant.now(), status.value(), "Recurso não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // Trata erros de falha na autenticação (login/senha errados)
    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<StandardError> authentication(AutenticacaoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED; // 401
        StandardError err = new StandardError(Instant.now(), status.value(), "Falha na Autenticação", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}

// Classe auxiliar para o corpo do erro
class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Instant timestamp;
    public Integer status;
    public String error;
    public String message;
    public String path;

    // Construtor vazio para serialização
    public StandardError() {
    }
    
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}