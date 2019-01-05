package com.truyenho.service;

import com.truyenho.model.Product;
import com.truyenho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  public ProductServiceImpl() {
  }

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public void save(Product product) {
    productRepository.put(product.getId(), product);
  }

  @Override
  public Product findById(int id) {
    return productRepository.get(id);
  }

  @Override
  public void update(int id, Product product) {
    productRepository.put(id, product);
  }

  @Override
  public void remove(int id) {
    productRepository.remove(id);
  }
}
