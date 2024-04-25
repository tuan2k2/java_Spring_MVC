package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product handleSaveProduct(Product product) {
        Product product2 = this.productRepository.save(product);
        System.out.println(product2);
        return product2;
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getUserById(long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

}
