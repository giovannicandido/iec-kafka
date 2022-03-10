package com.example.ecommerce.estoque.repository;

import com.example.ecommerce.estoque.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
