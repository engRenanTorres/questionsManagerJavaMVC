package br.com.engrenantorres.questionmanager.interceptor;


import br.com.engrenantorres.questionmanager.model.AccessModel;
import br.com.engrenantorres.questionmanager.repository.AccessModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

//intercepta request e responses
public class AccessInterceptor implements HandlerInterceptor {
  @Autowired
  private AccessModelRepository accessModelRepository;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    AccessModel access = new AccessModel();
    access.setPath(request.getRequestURI());
    access.setDate(LocalDateTime.now());

    request.setAttribute("access",access);

    return true;
  }
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    AccessModel access = (AccessModel) request.getAttribute("access");
    access.setDuration(Duration.between(access.getDate(), LocalDateTime.now()));

    accessModelRepository.save(access);

  }

}
