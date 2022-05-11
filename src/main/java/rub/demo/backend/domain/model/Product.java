package rub.demo.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rub.demo.backend.domain.dto.ProductRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "weight")
    private String weight;

    @ManyToMany
    @JoinColumn(name = "bin_id")
    private List<Bin> bin;

    public ProductRequest convertProductToProductRequest(Product product) {
        return new ProductRequest().builder()
                .name(product.getName())
                .price(product.getPrice())
                .weight(product.getWeight()).build();
    }



}
