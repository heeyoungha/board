package board.aop;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class RequestLoggingAspect {

    @Around(value = "restController() && getMapping()")
    public Object query(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Operation operation = method.getAnnotation(Operation.class);
        GetMapping getMapping = method.getAnnotation(GetMapping.class);

        Object result;
        try {
            log.info(operation.summary());
            log.info(Arrays.toString(getMapping.value()));
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    protected void getMapping() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    protected void restController() {
    }
}
