package com.shop.vendasonline.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.model.Venda;
import com.shop.vendasonline.repository.VendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VendaService {
    
    private final VendaRepository vendaRepository;

    public Venda saveVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Optional<Venda> findVendaById(Long id) {
        return vendaRepository.findById(id);
    }

    public void deleteVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    public List<Venda> findAllVendas() {
        return vendaRepository.findAll();
    }

    public Page<Venda> findAllVendas(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    @Transactional
    public Venda updateVenda(Venda venda) {
        Venda vendaExistente = vendaRepository.findById(venda.getId())
                .orElseThrow(() -> new IllegalArgumentException("Venda not found with id: " + venda.getId()));

        vendaExistente.setDataVenda(venda.getDataVenda());
        vendaExistente.setDataCancelamento(venda.getDataCancelamento());
        vendaExistente.setMotivoCancelamento(venda.getMotivoCancelamento());
        vendaExistente.setObservacoes(venda.getObservacoes());

        return vendaRepository.save(vendaExistente);
    }

    public long countVendas() {
        return vendaRepository.count();
    }

    public void deleteAllVendas() {
        vendaRepository.deleteAll();
    }

    public List<Venda> encontrarVendasMaisRecentes(int page, int size) {
        return vendaRepository.findAllByOrderByDataVendaDesc(Pageable.ofSize(size).withPage(page));
    }

    public List<Venda> encontrarVendasMaisAntigas(int page, int size) {
        return vendaRepository.findAllByOrderByDataVendaAsc(Pageable.ofSize(size).withPage(page));
    }
    
}
