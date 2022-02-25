package servlet;

import manager.ItemManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddItemServlet extends HttpServlet {

    private final String UPLOAD_DIR = "/Users/nona.asatryan/IdeaProjects/maven/myItems.am/upload";
    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Item item = Item.builder()
                .title(title)
                .price(price)
                .categoryId(categoryId)
                .userId(user.getId())
                .build();

        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
                item.setPictureUrl(fileName);
            }
        }
        itemManager.add(item);
        resp.sendRedirect("/userHome");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
