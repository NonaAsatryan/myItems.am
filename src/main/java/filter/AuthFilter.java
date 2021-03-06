package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addItem","/myItems"})
public class AuthFilter implements Filter {

@Override
public void init(FilterConfig filterConfig) throws ServletException {

}

@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    Object user = request.getSession().getAttribute("user");
    if (user == null) {
        response.sendRedirect("/home");
    } else {
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

@Override
public void destroy() {
}
}
