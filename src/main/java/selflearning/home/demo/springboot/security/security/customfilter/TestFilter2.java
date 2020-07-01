package selflearning.home.demo.springboot.security.security.customfilter;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@Component
//@Order(1)
public class TestFilter2 implements Filter {
    public TestFilter2() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter2 inited");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter2 is called");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter2 destroyed");
    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("the ip address of client is: " + httpServletRequest.getRemoteUser());
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//
//    }
}
