package com.truyenho;

import com.truyenho.repository.ProductRepository;
import com.truyenho.repository.ProductRepositoryImpl;
import com.truyenho.service.ProductService;
import com.truyenho.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.truyenho"})
public class AppConfig {
  @Bean(name = "productService")
  public ProductService getProductService() {
    return new ProductServiceImpl();
  }

  @Bean(name = "productRepository")
  public ProductRepository getProductRepository() {
    return new ProductRepositoryImpl();
  }
}
