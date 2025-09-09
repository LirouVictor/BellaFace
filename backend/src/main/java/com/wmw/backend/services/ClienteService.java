package com.wmw.backend.services;

import com.wmw.backend.dto.LoginRequestDTO;
import com.wmw.backend.dto.LoginResponseDTO;
import com.wmw.backend.entities.Cliente;
import com.wmw.backend.repository.ClienteRepository;
import com.wmw.backend.services.exception.AutenticacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public LoginResponseDTO autenticar(LoginRequestDTO loginRequest) {
        // Busca o cliente pelo login no banco de dados
        Cliente cliente = clienteRepository.findByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new AutenticacaoException("Login ou senha inválidos."));

        // Compara a senha enviada com a senha armazenada no banco
        if (!cliente.getSenha().equals(loginRequest.getSenha())) {
            throw new AutenticacaoException("Login ou senha inválidos.");
        }

        // Se a autenticação for bem-sucedida, retorna o DTO de resposta
        return new LoginResponseDTO(cliente.getId(), cliente.getNome());
    }
}