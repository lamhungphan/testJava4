package com.fpoly.testjava4.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ProductDao productDao = new ProductDaoImpl();
    public CategoryDao categoryDao = new CategoryDaoImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        switch (path) {
            case "/product":
                doGetProduct(request, response);
                break;
            case "/category":
                doGetCategory(request, response);
                break;
            case "/statistic":
                doGetStatistic(request, response);
                break;
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        String path = request.getServletPath();

        if ("/category".equals(path)) {
            doPostCategory(request, response, action);
        } else if ("/product".equals(path)) {
            doPostProduct(request, response, action);
        }
    }

    private void doGetProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Lấy danh sách categories
        List<Category> categories = categoryDao.findAll();
        request.setAttribute("categories", categories);

        // Lấy danh sách sản phẩm
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products == null) {
            // Nếu không có sản phẩm, lấy tất cả sản phẩm
            products = productDao.findAll();
        }
        request.setAttribute("products", products);
        request.setAttribute("view", "/product.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doGetCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = categoryDao.findAll();
        request.setAttribute("categories", categories);
        request.setAttribute("view", "/category.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doGetStatistic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Tạo danh sách để lưu thông tin thống kê
        List<Map<String, Object>> statistics = new ArrayList<>();

        // Lấy tất cả các category
        List<Category> categories = categoryDao.findAll();

        // Lặp qua tất cả các category và lấy số lượng sản phẩm cho mỗi category
        for (Category category : categories) {
            Integer productCount = productDao.countByCategory(category.getId());
            Map<String, Object> stat = new HashMap<>();
            stat.put("categoryName", category.getName());
            stat.put("productCount", productCount);
            statistics.add(stat);
        }

        // Truyền danh sách thống kê vào request
        request.setAttribute("statistics", statistics);

        // Chuyển tiếp đến trang statistic.jsp
        request.setAttribute("view", "/statistic.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void doPostProduct(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        try {
            switch (action.toLowerCase()) {
                case "filter":
                    // Lọc sản phẩm theo category
                    String categoryFilterCode = request.getParameter("category_id");
                    if (categoryFilterCode != null && !categoryFilterCode.trim().isEmpty() && !categoryFilterCode.equals("all")) {
                        // Lọc sản phẩm theo category code
                        List<Product> products = productDao.getProductsByCategory(categoryFilterCode);
                        request.setAttribute("products", products);
                    } else {
                        List<Product> products = productDao.findAll();
                        request.setAttribute("products", products);
                    }
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred: " + e.getMessage());
        }
        doGetProduct(request, response);
    }


    private void doPostCategory(HttpServletRequest request, HttpServletResponse response, String action) throws IOException, ServletException {
        try {
            switch (action.toLowerCase()) {
                case "create":
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");

                    if (id == null || name == null || id.trim().isEmpty() || name.trim().isEmpty()) {
                        request.setAttribute("message", "Category ID and Name cannot be empty.");
                    } else {
                        categoryDao.create(new Category(id, name));
                        request.setAttribute("message", "Category created successfully.");
                    }
                    break;

                case "delete":
                    id = request.getParameter("id");

                    Integer productCount = productDao.countByCategory(id);
                    if (productCount > 0) {
                        request.setAttribute("message", "Cannot delete category. It has associated products.");
                    } else {
                        categoryDao.delete(id);
                        request.setAttribute("message", "Category deleted successfully.");
                    }
                    break;

                case "edit":
                    id = request.getParameter("id");
                    Category category = categoryDao.findById(id);
                    request.setAttribute("editCategory", category);
                    break;
                default:
                    request.setAttribute("message", "Unknown action: " + action);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred: " + e.getMessage());
        }
        doGetCategory(request, response);
    }

}