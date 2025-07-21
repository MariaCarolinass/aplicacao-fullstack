package com.shop.vendasonline.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    List<Pedido> findByStatus(Status status);

    List<Pedido> findByClienteId(Long clienteId);

    Pedido findByNumeroPedido(Integer numeroPedido);
    
    long countByClienteId(Long clienteId);

    @Query("SELECT SUM(p.preco - COALESCE(p.desconto, 0)) FROM Produto p")
    Double sumValorTotal();
    
}
