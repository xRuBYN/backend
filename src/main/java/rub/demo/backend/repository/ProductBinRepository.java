package rub.demo.backend.repository;

import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rub.demo.backend.domain.model.Bin;
import rub.demo.backend.domain.model.Product;

import java.util.List;

@Repository
public interface ProductBinRepository extends JpaRepository<Bin,Product> {
    @Transactional
    @Modifying
    @Query(value = "insert into product_bin (product_id, bin_id) values (:idProduct, :idBin)",nativeQuery = true)
    void customSave(@Param("idProduct") Long idProduct, @Param("idBin") Long idBin);

}
