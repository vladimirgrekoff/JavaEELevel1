package com.grekoff.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// http://localhost:8080/lesson1/list_products
@WebServlet(name = "ProductsServlet", urlPatterns = "/list_products" )
public class ProductsServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductsServlet.class);
    private String[][] arrayProductNameAndCost;
    private Product[] products;


    @Override
    public void init(){
        arrayProductNameAndCost = new String[][]{{"хлеб", "40"}, {"масло", "100"}, {"сыр", "150"}, {"молоко", "80"}, {"кефир", "75"}, {"макароны", "75.99"}, {"печенье", "60"}, {"конфеты", "90"}, {"мандарины", "130.50"}, {"яблоки", "150.20"}};
        products = new Product[10];
        for (int i = 0; i < 10; i++){
            products[i] = new Product(i+1, arrayProductNameAndCost[i][0], Float.parseFloat(arrayProductNameAndCost[i][1]));
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Log: Products");

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>Список продуктов</h2>");
        for (Product p: products) {
            out.println("<p>" + p.toString() + "</p>");
        }
        out.println("</body></html>");
        out.close();
    }
}
