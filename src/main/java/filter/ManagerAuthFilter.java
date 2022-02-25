package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/userHome","/logout"})
public class ManagerAuthFilter implements Filter {

@Override
public void init(FilterConfig filterConfig) throws ServletException {

}

@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("/home.jsp");
    } else {
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

@Override
public void destroy() {
}
}
