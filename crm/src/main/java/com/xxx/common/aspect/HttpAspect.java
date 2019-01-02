package com.xxx.common.aspect;

import com.xxx.common.framework.exception.LoginException;
import com.xxx.common.framework.exception.PermissionException;
import com.xxx.common.interfaces.NotLogin;
import com.xxx.common.interfaces.Permission;
import com.xxx.common.util.ClassUtil;
import com.xxx.model.sys.entity.SysUser;
import com.xxx.model.sys.service.SysMenuService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class HttpAspect {

    private final static Logger LOG = LoggerFactory.getLogger(HttpAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal();

    @Autowired
    private SysMenuService sysMenuService;

    @Pointcut("execution(public * com.xxx.model.*.controller.*.*(..))")
    public void poin() {}

    @Before("poin()")
    public void before(JoinPoint joinpoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        startTime.set(System.currentTimeMillis());//
        System.out.println("doBefore==>"+request.getRemoteAddr()+"==>"+request.getRequestURI()+"==>" + Arrays.toString(joinpoint.getArgs()));
        Class c = ClassUtil.getClass(joinpoint);
        Method method = ClassUtil.getMethod(joinpoint);
        if(velidationLogin(c,method)){
            //登录认证
            HttpSession session = request.getSession();
            SysUser user = (SysUser) session.getAttribute("loginUser");
            if( user == null ){
                request.getRequestDispatcher("/login").forward(request, response);
                throw new LoginException();
            }else if(validationPermission(user, method) && !Long.valueOf(1L).equals(user.getId())){
                //权限认证
                request.getRequestDispatcher("/error/noPermission").forward(request, response);
                throw new PermissionException();
            }
        }
    }

    @AfterReturning("poin()")
    public void afterReturning(JoinPoint joinpoint) throws Exception {
        System.out.println("doAfter==>"+(System.currentTimeMillis()-startTime.get()));
    }

    //判断用户是否需要登录认证 需要认证返回true 不需要认证返回false
    private boolean velidationLogin(Class c,Method method){
        //类状态,方法状态
        boolean classStatus = true,methodStatus = true;
        //类,方法是否存在注解
        boolean classHas = false,methodHas = false;
        if(c.isAnnotationPresent(NotLogin.class)){
            NotLogin notLogin = (NotLogin)c.getAnnotation(NotLogin.class);
            classStatus = notLogin.value();
            classHas = true;
        }

        if(method.isAnnotationPresent(NotLogin.class)){
            NotLogin notLogin = (NotLogin)method.getAnnotation(NotLogin.class);
            methodStatus = notLogin.value();
            methodHas = true;
        }
        //当方法注解状态为false时 或者 当方法与类上都没有notLogin注解时 或者 当类的注解为false并且方法没有notLogin注解时 满足以上条件需要登录认证
        if( methodStatus == false || ( !classHas && !methodHas) || (classStatus==false && !methodHas) ){
            return true;
        }
        return false;
    }

    //判断当前用户访问权限 有访问权限返回false 无访问权限返回true
    private boolean validationPermission(SysUser sysUser,Method method){
        if(method.isAnnotationPresent(Permission.class)){
            Permission permission = method.getAnnotation(Permission.class);
            List<String> permissions = sysMenuService.getPermissionByCache(sysUser.getId());
            if(!permissions.contains(permission.value())){
                return true;
            }
        }
        return false;
    }
}
