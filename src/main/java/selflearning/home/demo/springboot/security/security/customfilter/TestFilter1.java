package selflearning.home.demo.springboot.security.security.customfilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
@Component
@Order(3)
public class TestFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("init method");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("the request remove host is:" + servletRequest.getRemoteHost());
        System.out.println("Filter1 is called");
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("Destroyed method");
    }
}
