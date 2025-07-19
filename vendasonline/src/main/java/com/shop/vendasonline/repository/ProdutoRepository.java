package com.shop.vendasonline.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    Produto findByCodigo(String codigo);

    List<Produto> findByPedidoId(Long pedidoId);
    
    boolean existsByCodigo(String codigo);

}
