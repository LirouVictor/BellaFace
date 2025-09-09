package com.wmw.backend.services.exception;

// Esta exceção será usada quando uma regra de negócio for violada,
// como tentar criar um pedido no fim de semana.
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String message) {
        super(message);
    }
}