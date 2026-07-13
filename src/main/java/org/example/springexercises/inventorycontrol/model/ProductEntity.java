package org.example.springexercises.inventorycontrol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springexercises.inventorycontrol.enums.ProductStatus;

import java.math.BigDecimal;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantityInInventory;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = ProductStatus.SOLD_OUT;
        }
    }
}
