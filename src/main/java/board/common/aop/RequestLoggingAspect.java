package board.common.aop;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RequestLoggingAspect extends CommonPointCut {

    //조회
    @Around(value = "restController() && getMapping()")
    public Object query(ProceedingJoinPoint joinPoint) {
        Object result;
        try {
            log.error(getOperation(joinPoint));
            log.error(getMappingURI(joinPoint));
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    //생성, 수정, 삭제
    @Around(value = "restController() && (PostMapping() || PutMapping() || PatchMapping() || DeleteMapping())")
    public Object usecase(ProceedingJoinPoint joinPoint) {
        Object result;

        try {
            MDC.put("adminId", "test");
            MDC.put("operation", getOperation(joinPoint));
            MDC.put("requestMapping",getMappingURI(joinPoint));
            result = joinPoint.proceed();
            MDC.clear();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private static String getOperation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(Operation.class)) {
            Operation operation = method.getAnnotation(Operation.class);

            return operation.summary();
        }

        return Strings.EMPTY;
    }

    private static String getMappingURI(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            return getString(getMapping.value());
        }
        if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            return getString(postMapping.value());
        }
        if (method.isAnnotationPresent(PutMapping.class)) {
            PutMapping putMapping = method.getAnnotation(PutMapping.class);
            return getString(putMapping.value());
        }
        if (method.isAnnotationPresent(PatchMapping.class)) {
            PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
            return getString(patchMapping.value());
        }
        if (method.isAnnotationPresent(DeleteMapping.class)) {
            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
            return getString(deleteMapping.value());
        }

        return Strings.EMPTY;
    }

    private static String getString(String[] mapping) {
        return mapping.length > 0 ? mapping[0] : Strings.EMPTY;
    }
}
