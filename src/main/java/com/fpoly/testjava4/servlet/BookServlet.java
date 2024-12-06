package com.fpoly.testjava4.servlet;

import com.fpoly.testjava4.dao.*;
import com.fpoly.testjava4.dao.Impl.AuthorDaoImpl;
import com.fpoly.testjava4.dao.Impl.BookDaoImpl;
import com.fpoly.testjava4.entity.Author;
import com.fpoly.testjava4.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/index", "/book", "/author"})
public class BookServlet extends HttpServlet {
    public BookDao bookDao = new BookDaoImpl();
    public AuthorDao authorDao = new AuthorDaoImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        switch (path) {
            case "/book":
                doGetBook(request, response);
                break;
            case "/author":
                doGetAuthor(request, response);
                break;
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        String path = request.getServletPath();

        if ("/author".equals(path)) {
            doPostAuthor(request, response, action);
        } else if ("/book".equals(path)) {
            doPostBook(request, response, action);
        }
    }

    private void doGetBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Book> books = bookDao.findAll();

        request.setAttribute("books", books);
        request.setAttribute("view", "/book.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doGetAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Author> authors = authorDao.findAll();
        request.setAttribute("authors", authors);
        request.setAttribute("view", "/author.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void doPostBook(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        try {
            if (action.equalsIgnoreCase("show_book")) {
                String authorId = request.getParameter("id");
                if (authorId != null && !authorId.trim().isEmpty()) {
                    List<Book> books = bookDao.findBooksByAuthor(authorId);
                    request.setAttribute("books", books);
                    request.setAttribute("totalBook", books.size());
                    request.setAttribute("view", "/book.jsp");
                    request.getRequestDispatcher("/book.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred: " + e.getMessage());
        }
        doGetBook(request, response);
    }

    private void doPostAuthor(HttpServletRequest request, HttpServletResponse response, String action) throws IOException, ServletException {
        try {
            switch (action.toLowerCase()) {
                case "create":
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");

                    Author author = new Author();
                    author.setId(id);
                    author.setName(name);

                    if (id == null || name == null || id.trim().isEmpty() || name.trim().isEmpty()) {
                        request.setAttribute("message", "Category ID and Name cannot be empty.");
                    } else {
                        authorDao.create(author);
                        request.setAttribute("message", "Category created successfully.");
                    }
                    break;

                case "delete":
                    id = request.getParameter("id");

                    Integer productCount = bookDao.countByAuthor(id);
                    if (productCount > 0) {
                        request.setAttribute("message", "Cannot delete Author. It has associated products.");
                    } else {
                        authorDao.delete(id);
                        request.setAttribute("message", "Author deleted successfully.");
                    }
                    break;

                case "edit":
                    id = request.getParameter("id");
                    Author editAuthor = authorDao.findById(id);
                    request.setAttribute("editAuthor", editAuthor);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred: " + e.getMessage());
        }
        doGetAuthor(request, response);
    }
}