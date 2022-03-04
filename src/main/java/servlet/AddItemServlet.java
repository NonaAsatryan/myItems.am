package servlet;

import manager.CategoryManager;
import manager.ItemImageManager;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddItemServlet extends HttpServlet {

    private static final String IMAGE_PATH = "/Users/nona.asatryan/IdeaProjects/maven/myItems.am/images";
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private ItemImageManager itemImageManager = new ItemImageManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllICategories());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Part filePart = req.getPart("itemImage");
        String fileName = filePart.getSubmittedFileName();
        String imageUrl = System.nanoTime() + "_" + fileName;
        filePart.write(IMAGE_PATH + imageUrl);

        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int catId = Integer.parseInt(req.getParameter("cat_id"));
        Category category = categoryManager.getCategoryById(catId);
        String description = req.getParameter("description");
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(category)
                .user(user)
                .description(description)
                .build();
        itemManager.add(item);
        resp.sendRedirect("/home");
    }
}
