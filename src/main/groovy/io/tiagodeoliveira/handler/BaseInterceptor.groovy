package io.tiagodeoliveira.handler

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by tiago on 20/06/15.
 */
class BaseInterceptor extends HandlerInterceptorAdapter {
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler
        modelAndView.addObject("controller", handlerMethod.getBean().getClass().getSimpleName())
        modelAndView.addObject("action", handlerMethod.getMethod().getName())
    }
}
