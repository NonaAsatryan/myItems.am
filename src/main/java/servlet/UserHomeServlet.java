package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Item> allItemsByUserId = itemManager.getAllItemsByUserIdLimit(user.getId());
        List<Category> categoryList = categoryManager.getAllICategories();

        req.setAttribute("items", allItemsByUserId);
        req.setAttribute("categories", categoryList);
        req.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(req, resp);


    }
}
