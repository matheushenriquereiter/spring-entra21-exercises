package org.example.springexercises.inventorycontrol.repository;

import org.example.springexercises.inventorycontrol.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
