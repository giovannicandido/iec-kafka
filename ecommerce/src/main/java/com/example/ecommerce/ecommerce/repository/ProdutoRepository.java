package com.example.ecommerce.ecommerce.repository;

import com.example.ecommerce.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
