package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    // "/"에 대한 요청 시 로그인 상태 체크
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(request.getSession().getAttribute("loginID") != null) {
            return true;
        } else {
            response.sendRedirect("/login");
            return false;
        }

    } // preHandle()

}
