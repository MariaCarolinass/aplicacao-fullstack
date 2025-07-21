package com.shop.vendasonline.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Transactional
    public Cliente updateCliente(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEndereco(cliente.getEndereco());
        
        return clienteRepository.save(clienteExistente);
    }

    public List<Cliente> encontrarClientesMaisAtivos(int page, int size) {
        return clienteRepository.encontrarClientesMaisAtivos(Pageable.ofSize(size).withPage(page));
    }

    public Cliente findClienteByEmail(String email) {
        return clienteRepository.findByEmail(email).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.findByEmail(email).isPresent();
    }

    public long countClientes() {
        return clienteRepository.count();
    }

    public void deleteAllClientes() {
        clienteRepository.deleteAll();
    }

}
