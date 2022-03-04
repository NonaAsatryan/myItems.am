package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/Users/nona.asatryan/IdeaProjects/maven/myItems.am/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageUrl = req.getParameter("image_url");
        String filePath = UPLOAD_DIR + imageUrl;
        File downloadFile = new File(filePath);
            if (downloadFile.exists()) {
                try (FileInputStream inStream = new FileInputStream(downloadFile)) {
                    resp.setContentType("image/jpeg");
                    resp.setContentLength((int) downloadFile.length());
                    OutputStream outStream = resp.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }
}
