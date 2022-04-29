package dev.simmons.aspects;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    static int requestCounter = 0;

    @Before("logJP()")
    public void logCounter() {
        requestCounter++;
        System.out.println("Log has been called " + requestCounter + " times");
    }

    @After("logJP()")
    public static void finished() {
        System.out.println("Request has finished processing.");
    }

    /*@AfterThrowing(pointcut="errorJP()", throwing = "ex")
    public static void error(Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Pointcut("within(dev.simmons.data..*)")
    private void errorsJP() {}
*/
    @Pointcut("within(dev.simmons.controllers..*)")
    private void logJP() {}
}
