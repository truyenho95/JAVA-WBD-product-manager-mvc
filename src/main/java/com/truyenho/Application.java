package com.truyenho;

import com.truyenho.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    ProductService productService = applicationContext.getBean("productService", ProductService.class);

    System.out.println(productService.findAll().get(0).getName());
  }
}
