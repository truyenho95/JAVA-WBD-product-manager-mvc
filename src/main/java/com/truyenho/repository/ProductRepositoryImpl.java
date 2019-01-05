package com.truyenho.repository;

import com.truyenho.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
  private static Map<Integer, Product> products;

  static {
    products = new HashMap<>();
    products.put(1, new Product(1, "Apple", 10));
    products.put(2, new Product(2, "Mango", 5));
    products.put(3, new Product(3, "Strawberry", 15));
    products.put(4, new Product(4, "Banana", 6));
    products.put(5, new Product(5, "Water Melon", 10));
  }

  @Override
  public List<Product> findAll() {
    return new ArrayList<Product>(products.values());
  }

  @Override
  public void put(int id, Product product) {
    products.put(id, product);
  }

  @Override
  public Product get(int id) {
    return products.get(id);
  }

  @Override
  public void remove(int id) {
    products.remove(id);
  }
}
