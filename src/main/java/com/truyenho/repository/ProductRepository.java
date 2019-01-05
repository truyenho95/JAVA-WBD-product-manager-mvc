package com.truyenho.repository;

import com.truyenho.model.Product;

import java.util.List;

public interface ProductRepository {
  List<Product> findAll();

  void put(int id, Product product);

  Product get(int id);

  void remove(int id);
}
