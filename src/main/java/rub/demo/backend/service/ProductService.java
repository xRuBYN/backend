package rub.demo.backend.service;

import rub.demo.backend.domain.dto.ProductFromUser;
import rub.demo.backend.domain.dto.ProductRequest;
import rub.demo.backend.domain.dto.ResponseProductAdd;

import java.util.List;

public interface ProductService {
    ProductRequest findById(Long id);
    ResponseProductAdd addProductFromUser(Long id, String email);
    void edit(Long id,ProductRequest productRequest);
    List<ProductFromUser> getProductFromUser(Long id);
}
