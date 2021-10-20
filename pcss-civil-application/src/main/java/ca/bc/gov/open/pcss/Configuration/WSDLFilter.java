package ca.bc.gov.open.pcss.Configuration;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;

@Component
public class WSDLFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if ("wsdl".equalsIgnoreCase(httpRequest.getQueryString())) {
            HttpServletRequestWrapper requestWrapper =
                    new HttpServletRequestWrapper(httpRequest) {
                        @Override
                        public String getQueryString() {
                            return null;
                        }

                        @Override
                        public String getRequestURI() {
                            return super.getRequestURI() + ".wsdl";
                        }
                    };
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
