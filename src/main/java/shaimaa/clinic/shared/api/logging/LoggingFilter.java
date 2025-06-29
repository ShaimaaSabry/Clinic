package shaimaa.clinic.shared.api.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
class LoggingFilter extends OncePerRequestFilter {
  private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);

    logRequest(requestWrapper);
    logResponse(responseWrapper);
  }

  void logRequest(ContentCachingRequestWrapper requestWrapper) {
    String method = requestWrapper.getMethod();
    String url = requestWrapper.getRequestURI();
    String body = requestWrapper.getContentAsString();

    LOG.info("Request [{} {}]: {}", method, url, body);
  }

  void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
    String status = String.valueOf(responseWrapper.getStatus());
    String body = new String(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());

    LOG.info("Response [{}]: {}", status, body);

    responseWrapper.copyBodyToResponse();
  }
}
