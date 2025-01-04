package com.service;

import com.model.Product;
import com.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAllByDisabledEquals(false);
    }

    @Override
    public Product save(Product product) {
        product.setDisabled(false);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
//        productRepository.deleteById(id);
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDisabled(true);
            productRepository.save(product);
        }
    }
}
