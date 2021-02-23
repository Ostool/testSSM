package wky.ssm.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import wky.ssm.domain.SysLog;
import wky.ssm.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

public class LogAopOne {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date visitTime;
    private String url;
    private String ip;
    private long time;
    private Object rtValue;
    private Object[] args;
    private Method method;
    private Class clazz;
    private String username;
    //环绕通知
    public Object arrounLog(ProceedingJoinPoint jp)throws Throwable{
        visitTime = new Date();
        ip = request.getRemoteAddr();
        args = jp.getArgs();
        clazz = jp.getTarget().getClass();

        rtValue = jp.proceed(args);

        String methodName = jp.getSignature().getName();//获得方法名
        if (args==null&&args.length==0){
            method = clazz.getMethod(methodName);
        }else {
            Class[] clazzArgs = new Class[args.length];
            for (int i=0;i<args.length;i++){
                clazzArgs[i]=args[i].getClass();
            }
            method = clazz.getMethod(methodName,clazzArgs);//有参方法
        }

        //获取用户名
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        username = user.getUsername();

        //获取url
        if (clazz!=null&&method!=null&&clazz!=LogAopOne.class){
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation!=null){
                String[] classValue = clazzAnnotation.value();

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                }
            }
        }


        time = new Date().getTime()-visitTime.getTime();
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(time);
        sysLog.setMethod(methodName);
        sysLog.setUrl(url);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUsername(username);
        sysLogService.saveSysLog(sysLog);

        return rtValue;
    }
}
