package ch.heg.initiative.web;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/write/initiative")
public class IntiativeAdminFilter implements Filter {

    private static String AUTH_HEADER_NAME = "INITIATIVE_TOKEN";
    private static String ACCEPTED_SECRET = "12345";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (request instanceof HttpServletRequest req) {
            String secret = req.getHeader(AUTH_HEADER_NAME);
            System.out.println(secret);
            System.out.println(ACCEPTED_SECRET);
            System.out.println(secret.equals(ACCEPTED_SECRET));
            if (secret.equals(ACCEPTED_SECRET)) chain.doFilter(request, response);
        }
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
    }
}
