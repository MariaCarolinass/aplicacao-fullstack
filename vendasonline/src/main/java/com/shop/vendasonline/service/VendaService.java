package com.shop.vendasonline.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Venda;
import com.shop.vendasonline.repository.VendaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VendaService {
    
    private final VendaRepository vendaRepository;

    public void saveVenda(Venda venda) {
        vendaRepository.save(venda);
    }

    public Venda findVendaById(Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public void deleteVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    public List<Venda> findAllVendas() {
        return vendaRepository.findAll();
    }

    public void updateVenda(Venda venda) {
        Venda vendaExistente = vendaRepository.findById(venda.getId())
                .orElseThrow(() -> new IllegalArgumentException("Venda not found with id: " + venda.getId()));

        vendaExistente.setDataVenda(venda.getDataVenda());
        vendaExistente.setDataCancelamento(venda.getDataCancelamento());
        vendaExistente.setMotivoCancelamento(venda.getMotivoCancelamento());
        vendaExistente.setObservacoes(venda.getObservacoes());
        vendaExistente.setPedido(venda.getPedido());

        vendaRepository.save(vendaExistente);
    }

    public long countVendas() {
        return vendaRepository.count();
    }

    public void deleteAllVendas() {
        vendaRepository.deleteAll();
    }

    public List<Venda> findByClienteId(Long clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }

    public List<Venda> encontrarVendasMaisRecentes(int page, int size) {
        return vendaRepository.findAllByOrderByDataVendaDesc(Pageable.ofSize(size).withPage(page));
    }

    public List<Venda> encontrarVendasMaisAntigas(int page, int size) {
        return vendaRepository.findAllByOrderByDataVendaAsc(Pageable.ofSize(size).withPage(page));
    }

    public List<Venda> encontrarVendasPorCliente(Long clienteId, int page, int size) {
        return vendaRepository.findByClienteIdOrderByDataVendaDesc(clienteId, Pageable.ofSize(size).withPage(page));
    }

    public List<Venda> findAllByOrderByDataVendaDesc() {
        return vendaRepository.findAllByOrderByDataVendaDesc();
    }

    public List<Venda> findAllByOrderByDataVendaAsc() {
        return vendaRepository.findAllByOrderByDataVendaAsc();
    }

}
