package demo.service;

import demo.entity.Product;
import demo.repository.ProductPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImple implements ProductService {

    @Autowired
    ProductPaginationRepository productPaginationRepository;


    @Override
    public List<Product> getAll(int page, int limit) {
        return productPaginationRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productPaginationRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productPaginationRepository.save(product);
    }

    @Override
    public boolean update(Product product, int id) {
        Optional<Product> optionalProduct = productPaginationRepository.findById(id);
        if(optionalProduct.isPresent()) {
            Product existProduct = optionalProduct.get();
            existProduct.setName(product.getName());
            existProduct.setPrice(product.getPrice());
            existProduct.setQuantity(product.getQuantity());
            productPaginationRepository.save(existProduct);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Optional<Product> optionalProduct = productPaginationRepository.findById(id);
        if(optionalProduct.isPresent()) {
            productPaginationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
