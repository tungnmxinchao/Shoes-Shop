/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard.product;

import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Categories;
import entity.Products;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Vector;
import java.sql.Date;

/**
 *
 * @author TNO
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "EditProduct", urlPatterns = {"/edit-product"})
public class EditProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));

            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            Vector<Categories> categories = categoryDAO.findAll();
            Products product = productDAO.searchProductById(productId);

            request.setAttribute("product", product);
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("views/dashboard/edit-product.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate input parameters
            if (request.getParameter("productID") == null || request.getParameter("productName") == null
                    || request.getParameter("price") == null || request.getParameter("quantity") == null
                    || request.getParameter("categoryID") == null || request.getParameter("importDate") == null
                    || request.getParameter("usingDate") == null || request.getParameter("status") == null) {
                throw new IllegalArgumentException("Missing required parameters");
            }

            int productId = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryId = Integer.parseInt(request.getParameter("categoryID"));
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            Date usingDate = Date.valueOf(request.getParameter("usingDate"));
            int status = Integer.parseInt(request.getParameter("status"));

            Part filePart = request.getPart("image");
            String imageFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String imagePath = null;
            if (imageFileName != null && !imageFileName.isEmpty()) {
                // Ensure the upload directory exists
                String uploadDir = getServletContext().getRealPath("/") + "uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                // Save the uploaded file to the server
                filePart.write(uploadDir + imageFileName);
                imagePath = "uploads/" + imageFileName;
            } else {
                ProductDAO productDAO = new ProductDAO();
                String imageExist = productDAO.searchProductById(productId).getImage();

                imagePath = imageExist;
            }

            Categories category = new Categories(categoryId, null);
            Products product = new Products(productId, productName, imagePath, price, quantity, category, importDate, usingDate, status);

            ProductDAO productDAO = new ProductDAO();
            boolean isUpdated = productDAO.updateProductByID(product);

            if (isUpdated) {
                response.sendRedirect("edit-product?productId=" + productId);
            } else {
                throw new Exception("Update failed");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid input data: " + e.getMessage());
            request.getRequestDispatcher("views/dashboard/edit-product.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("views/dashboard/edit-product.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
