package com.shop.vendasonline.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public void updateCliente(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente not found with id: " + cliente.getId()));
        
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setPedidos(cliente.getPedidos());
        
        clienteRepository.save(clienteExistente);
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
