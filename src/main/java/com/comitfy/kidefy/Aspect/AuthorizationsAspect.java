package com.comitfy.kidefy.Aspect;

import com.comitfy.kidefy.annotation.CheckAuth;
import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.util.common.HelperService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class AuthorizationsAspect {
    @Autowired
    private HelperService helperService;



    @Around("@annotation(checkAuth)")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint, CheckAuth checkAuth) throws Throwable {

        ControllerInfo controllerInfo = joinPoint.getTarget().getClass().getAnnotation(ControllerInfo.class);

        String controllerName = controllerInfo != null ? controllerInfo.controllerName() : "Unknown Controller";


        String permission = "Permission" + "." + controllerName + "." + checkAuth.permission();

       if (!helperService.hasPermission(permission)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You do not have permission to access this resource.");
        }



        return joinPoint.proceed();
    }
}
