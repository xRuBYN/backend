package rub.demo.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rub.demo.backend.domain.dto.ProductFromUser;
import rub.demo.backend.domain.dto.ProductRequest;
import rub.demo.backend.domain.dto.ResponseProductAdd;
import rub.demo.backend.domain.model.Bin;
import rub.demo.backend.domain.model.Product;
import rub.demo.backend.domain.model.User;
import rub.demo.backend.repository.BinRepository;
import rub.demo.backend.repository.ProductBinRepository;
import rub.demo.backend.repository.ProductRepository;
import rub.demo.backend.repository.UserRepository;
import rub.demo.backend.domain.projection.ProductProjection;
import rub.demo.backend.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductBinRepository productBinRepository;
    private final UserRepository userRepository;

    private final BinRepository binRepository;
    public ProductRequest findById(Long id) {
        Product product = repository.findAllById(id);
        return product.convertProductToProductRequest(product);
    }

    public ResponseProductAdd addProductFromUser(Long id, String email) {
        User user = userRepository.findByEmail(email);
        Product product = repository.findAllById(id);
        Bin bin = binRepository.findAllById(user.getId());
        List<Product> listProduct = new ArrayList<>();
        if(user != null) {
            if(product != null) {
                productBinRepository.customSave(product.getId(),bin.getId());
                return new ResponseProductAdd(true);
            }
            return new ResponseProductAdd(false);
        } else {
            return new ResponseProductAdd(false);
        }
    }
    private ProductFromUser convertProductProjectionToProductFromUser(ProductProjection productProjection) {
        return new ProductFromUser().builder()
                .name(productProjection.getName())
                .price(productProjection.getPrice()).build();
    }

    @Override
    public void edit(Long id,ProductRequest productRequest) {
        Product product = repository.findAllById(id);
        if(product != null) {
            if(!productRequest.getName().isEmpty()) {
                product.setName(productRequest.getName());
            }
            if(!productRequest.getPrice().isEmpty()) {
                product.setPrice(productRequest.getPrice());
            }
            if(!productRequest.getWeight().isEmpty()) {
                product.setWeight(productRequest.getWeight());
            }
            repository.save(product);
         }
    }

    @Override
    public List<ProductFromUser> getProductFromUser(Long id) {
        List<ProductProjection> list = repository.findByBinId(id);
        List<ProductFromUser> productFromUsers;
        productFromUsers = list.stream().map(this::convertProductProjectionToProductFromUser).collect(Collectors.toList());
        return productFromUsers;
    }


}
