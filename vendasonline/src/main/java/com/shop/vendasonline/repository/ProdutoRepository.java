package com.shop.vendasonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.vendasonline.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    Produto findByCodigo(String codigo);
    
    boolean existsByCodigo(String codigo);

}
