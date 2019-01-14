package com.truyenho.controller;

import com.truyenho.model.Product;
import com.truyenho.service.ProductService;
import com.truyenho.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/")
  public ModelAndView index(@ModelAttribute("success") String mess) {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("products", productService.findAll());
    modelAndView.addObject("success", mess);
    return modelAndView;
  }

  @GetMapping("/product/create")
  public ModelAndView create() {
    ModelAndView modelAndView = new ModelAndView("create");
    modelAndView.addObject("product", new Product());
    return modelAndView;
  }

  @PostMapping("/product/save")
  public ModelAndView save(Product product) {
    product.setId((int) (Math.random() * 10000));
    productService.save(product);
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    modelAndView.addObject("success", "Saved product successfully!");
    return modelAndView;
  }

  @GetMapping("/product/{id}/edit")
  public ModelAndView edit(@PathVariable int id) {
    ModelAndView modelAndView = new ModelAndView("edit");
    modelAndView.addObject("product", productService.findById(id));
    return modelAndView;
  }

  @GetMapping("/product/{id}/delete")
  public ModelAndView delete(@PathVariable int id) {
    ModelAndView modelAndView = new ModelAndView("delete");
    modelAndView.addObject("product", productService.findById(id));
    return modelAndView;
  }

  @PostMapping("/product/delete")
  public ModelAndView delete(Product product) {
    productService.remove(product.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    modelAndView.addObject("success", "Removed customer successfully!");
    return modelAndView;
  }

  @GetMapping("/product/{id}/view")
  public ModelAndView view(@PathVariable int id) {
    ModelAndView modelAndView = new ModelAndView("view");
    modelAndView.addObject("product", productService.findById(id));
    return modelAndView;
  }

}
