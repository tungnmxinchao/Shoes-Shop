/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard.product;

import dal.ProductDAO;
import entity.Categories;
import entity.Products;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "AddProduct", urlPatterns = {"/add-product"})
public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate input parameters
            if (request.getParameter("productName") == null || request.getParameter("price") == null
                    || request.getParameter("quantity") == null || request.getParameter("categoryID") == null
                    || request.getParameter("importDate") == null || request.getParameter("usingDate") == null
                    || request.getParameter("status") == null) {
                throw new IllegalArgumentException("Missing required parameters");
            }

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
              
                String uploadDir = getServletContext().getRealPath("/") + "uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }


                filePart.write(uploadDir + imageFileName);
                imagePath = "uploads/" + imageFileName;
            }

            Categories category = new Categories(categoryId, null);
            Products product = new Products(0, productName, imagePath, price, quantity, category, importDate, usingDate, status);

            ProductDAO productDAO = new ProductDAO();
            boolean isAdded = productDAO.insertProduct(product);

            if (isAdded) {
               response.sendRedirect("manage-product");
               return;
            } else {
                throw new Exception("Add failed");
            }
           
        } catch (IllegalArgumentException e) {
           response.sendRedirect("error.jsp");
        } catch (Exception e) {
             response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
