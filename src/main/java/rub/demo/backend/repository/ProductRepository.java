package rub.demo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rub.demo.backend.domain.model.Product;
import rub.demo.backend.domain.projection.ProductProjection;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * from product WHERE id =?1",nativeQuery = true)
    Product findByID(Long id);
    Product findAllById(Long id);

    @Query(value = "SELECT product.name, product.price from product join product_bin  on product.id = product_bin.product_id where bin_id = :id",nativeQuery = true)
    List<ProductProjection> findByBinId(@Param("id") Long id);
}
