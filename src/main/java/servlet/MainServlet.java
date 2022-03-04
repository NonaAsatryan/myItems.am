package servlet;

import manager.CategoryManager;
import manager.ItemImageManager;
import manager.ItemManager;
import model.Item;
import model.ItemImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class MainServlet extends HttpServlet {

    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();
    private ItemImageManager itemImageManager = new ItemImageManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException, IOException {
    String catIdStr = req.getParameter("cat_id");
    List<Item> items;
    if (catIdStr == null || catIdStr.equals("")) {
        items = itemManager.getLast20Items();
    } else {
        int catId = Integer.parseInt(catIdStr);
        items = itemManager.getLast20ItemsByCategory(catId);
    }
    req.setAttribute("items", items);
        for (Item item : items) {
            List<ItemImage> itemImages = itemImageManager.getAllItemImagesByItemId(item.getId());
            req.setAttribute("itemImages", itemImages);
        }
    req.setAttribute("categories", categoryManager.getAllICategories());
    req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }
}
