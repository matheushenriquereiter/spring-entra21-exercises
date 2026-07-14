package org.example.springexercises.inventorycontrol.controller;

import jakarta.validation.Valid;
import org.example.springexercises.inventorycontrol.dtos.ProductRequestDTO;
import org.example.springexercises.inventorycontrol.enums.ProductStatus;
import org.example.springexercises.inventorycontrol.model.ProductEntity;
import org.example.springexercises.inventorycontrol.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if (productEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(productEntityOptional.get());
    }

    @PostMapping
    public ResponseEntity<ProductEntity> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = new ProductEntity(productRequestDTO.name(), productRequestDTO.price(), productRequestDTO.quantityInInventory(), null);

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductEntity> deleteProduct(@PathVariable Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if (productEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productRepository.delete(productEntityOptional.get());

        return ResponseEntity.ok(productEntityOptional.get());
    }

    @PatchMapping("/{id}/restock")
    public ResponseEntity<ProductEntity> restockProduct(@PathVariable Long id, @RequestParam Integer quantity) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if (productEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setQuantityInInventory(productEntity.getQuantityInInventory() + quantity);

        if (productEntity.getQuantityInInventory() > 0) {
            productEntity.setStatus(ProductStatus.AVAILABLE);
        }

        return ResponseEntity.ok(productRepository.save(productEntity));
    }
}
