package com.shop.vendasonline.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    
    List<Venda> findByClienteId(Long clienteId);

    List<Venda> findAllByOrderByDataVendaDesc(Pageable pageable);

    List<Venda> findAllByOrderByDataVendaAsc(Pageable pageable);

    List<Venda> findByClienteIdOrderByDataVendaDesc(Long clienteId, Pageable pageable);

    List<Venda> findAllByOrderByDataVendaDesc();

    List<Venda> findAllByOrderByDataVendaAsc();

}
