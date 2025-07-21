package com.shop.vendasonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    Produto findByCodigo(String codigo);
    
    boolean existsByCodigo(String codigo);

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.pedido.id = :pedidoId")
    long countProdutosPorPedido(@Param("pedidoId") Long pedidoId);
    
    @Query("SELECT SUM(p.preco - COALESCE(p.desconto, 0)) FROM Produto p WHERE p.pedido.id = :pedidoId")
    Double sumPrecoProdutosPorPedido(@Param("pedidoId") Long pedidoId); 

}
