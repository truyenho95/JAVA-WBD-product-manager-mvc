package com.truyenho.controller;

import com.truyenho.AppConfig;
import com.truyenho.model.Product;
import com.truyenho.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductControllerServlet extends HttpServlet {
  private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

  private ProductService productService = applicationContext.getBean("productService", ProductService.class);

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null) {
      action = "";
    }
    switch (action) {
      case "create":
        createCustomer(request, response);
        break;
      case "edit":
        updateCustomer(request, response);
        break;
      case "delete":
        deleteCustomer(request, response);
        break;
      default:
        break;
    }
  }

  private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null) {
      dispatcher = request.getRequestDispatcher("views/error-404.jsp");
    } else {
      this.productService.remove(id);
      try {
        response.sendRedirect("/products");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    double price = Double.parseDouble(request.getParameter("price"));

    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null) {
      dispatcher = request.getRequestDispatcher("views/error-404.jsp");
    }
    else {
      product.setId(id);
      product.setName(name);
      product.setPrice(price);
      this.productService.update(id, product);

      request.setAttribute("product", product);
      request.setAttribute("message", "Product information was updated");
      dispatcher = request.getRequestDispatcher("views/edit.jsp");
    }
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
    int id = (int) (Math.random() * 10000);
    String name = request.getParameter("name");
    double price = Double.parseDouble(request.getParameter("price"));

    Product product = new Product(id, name, price);
    this.productService.save(product);
    RequestDispatcher dispatcher = request.getRequestDispatcher("views/create.jsp");
    request.setAttribute("message", "New product was created");
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null) {
      action = "";
    }
    switch (action) {
      case "create":
        showCreateForm(request, response);
        break;
      case "edit":
        showEditForm(request, response);
        break;
      case "delete":
        showDeleteForm(request, response);
        break;
      case "view":
        viewCustomer(request, response);
        break;
      default:
        listCustomers(request, response);
        break;
    }
  }

  private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null) {
      dispatcher = request.getRequestDispatcher("views/error-404.jsp");
    } else {
      request.setAttribute("product", product);
      dispatcher = request.getRequestDispatcher("views/delete.jsp");
    }
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null) {
      dispatcher = request.getRequestDispatcher("views/error-404.jsp");
    } else {
      request.setAttribute("product", product);
      dispatcher = request.getRequestDispatcher("views/edit.jsp");
    }
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }

  }

  private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
    RequestDispatcher dispatcher = request.getRequestDispatcher("views/create.jsp");
    try {
      dispatcher.forward(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void viewCustomer(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null) {
      dispatcher = request.getRequestDispatcher("views/error-404.jsp");
    } else {
      request.setAttribute("product", product);
      dispatcher = request.getRequestDispatcher("views/view.jsp");
    }
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void listCustomers(HttpServletRequest request, HttpServletResponse response) {
    List<Product> products = this.productService.findAll();
    request.setAttribute("products", products);

    RequestDispatcher dispatcher = request.getRequestDispatcher("views/list.jsp");
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }
}
