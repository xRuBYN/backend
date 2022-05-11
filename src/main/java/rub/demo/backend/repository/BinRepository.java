package rub.demo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rub.demo.backend.domain.model.Bin;

public interface BinRepository extends JpaRepository<Bin,Long> {
    Bin findAllById(Long id);

}
