package com.fpoly.testjava4.servlet;

import java.io.*;
import java.util.List;

import com.fpoly.testjava4.dao.CategoryDaoImpl;
import com.fpoly.testjava4.dao.ProductDaoImpl;
import com.fpoly.testjava4.entity.Category;
import com.fpoly.testjava4.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.fpoly.testjava4.dao.CategoryDao;
import com.fpoly.testjava4.dao.ProductDao;


@WebServlet({"/index", "/product", "/category", "/statistic"})
public class TestServlet extends HttpServlet {
    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        switch (path) {
            case "/index":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/product":
                doGetProduct(request, response);
                break;
            case "/category":
                doGetCategory(request, response);
                break;
            case "/statistic":
                doGetStatistic(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        switch (path) {

            case "/product":
                doPostProduct(request, response);
                break;
            case "/category":
                doPostCategory(request, response);
                break;
            case "/statistic":
                doPostStatistic(request, response);
                break;
        }
    }

    private void doGetProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = productDao.findAll();
        List<Category> categories = categoryDao.findAll();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    public void doGetCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }

    public void doGetStatistic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/statistic.jsp").forward(request, response);
    }

    private void doPostProduct(HttpServletRequest request, HttpServletResponse response) {
    }

    private void doPostCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        try {
            if ("create".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");

                if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty()) {
                    request.setAttribute("message", "Category ID and name cannot be empty.");
                } else {
                    // Tạo danh mục mới
                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);
                    categoryDao.create(category);
                    request.setAttribute("message", "Category created successfully.");
                }
            } else if ("delete".equalsIgnoreCase(action)) {
                String id = request.getParameter("id");

                // Kiểm tra xem danh mục có sản phẩm hay không
                int productCount = productDao.countByCategory(id);
                if (productCount > 0) {
                    request.setAttribute("message", "Cannot delete category. Products are associated with this category.");
                } else {
                    categoryDao.delete(id);
                    request.setAttribute("message", "Category deleted successfully.");
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred: " + e.getMessage());
        }

        // Refresh danh sách sau khi xử lý
        doGetCategory(request, response);
    }

    private void doPostStatistic(HttpServletRequest request, HttpServletResponse response) {
    }
}