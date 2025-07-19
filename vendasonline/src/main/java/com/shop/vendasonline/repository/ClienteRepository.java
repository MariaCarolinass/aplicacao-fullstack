package com.shop.vendasonline.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c LEFT JOIN c.pedidos p GROUP BY c ORDER BY COUNT(p) DESC")
    List<Cliente> encontrarClientesMaisAtivos(Pageable pageable);
    
}
