package wky.ssm.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class SysLogAdvice {
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("日志打印aroundPrintLog。。。前置");
            rtValue = pjp.proceed(args);
            System.out.println("日志打印aroundPrintLog。。。后置");
            return rtValue;
        }catch (Throwable t){
            System.out.println("日志打印aroundPrintLog。。。异常");
            throw new RuntimeException(t);
        }finally {
            System.out.println("日志打印aroundPrintLog...最终");
        }
    }



}
