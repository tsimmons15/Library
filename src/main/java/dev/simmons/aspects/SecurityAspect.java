package dev.simmons.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Aspect
public class SecurityAspect {

    @Around("securityJP()")
    public Object authenticate(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response.sendError(401, "No credentials provided.");
        } else if (auth.equals("password")) {
            return pjp.proceed();
        } else {
            response.sendError(403, "Your credentials are invalid");
        }

        return null;
    }

    //@Pointcut("@annotation(dev.simmons.aspects.Secured)")
    @Pointcut("execution(* dev.simmons.controllers.BookController.insertBook(..))")
    private void securityJP() {}
}
