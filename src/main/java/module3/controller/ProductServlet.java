package module3.controller;

import module3.model.Product;
import module3.service.category.CategoryService;
import module3.service.category.ICategoryService;
import module3.service.product.IProductService;
import module3.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet( value = "/products")
public class ProductServlet extends HttpServlet {
    ICategoryService categoryService = new CategoryService();
    IProductService productService = new ProductService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(req, resp);
                break;
            default:
                showAllProducts(req, resp);
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        req.setAttribute("categories", categoryService.findAll());
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewProduct(req, resp);
                break;
            default:
                showAllProducts(req, resp);
        }
    }

    private void createNewProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        String[] categoriesStr = req.getParameterValues("categories");
        int[]categories = new int[categoriesStr.length];
        for (int i = 0; i < categoriesStr.length; i++) {
            categories[i] = Integer.parseInt(categoriesStr[i]);
        }

        Product product = new Product(name, price,quantity,color,description);
        productService.save(product, categories);
    }
}
