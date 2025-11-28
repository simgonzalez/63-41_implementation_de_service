package ch.heg.tp08;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationFilter extends GenericFilterBean {

  private static final String AUTH_HEADER_NAME = "INITIATIVE_TOKEN";
  private static final String ACCEPTED_SECRET = "12345";

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String secret = ((HttpServletRequest) servletRequest).getHeader(AUTH_HEADER_NAME);
    if (secret != null && secret.equals(ACCEPTED_SECRET)) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      throw new ServletException("The token is not valid.");
    }
  }
}
