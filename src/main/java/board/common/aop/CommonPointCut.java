package board.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    protected void PostMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    protected void PutMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    protected void PatchMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    protected void DeleteMapping() {
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    protected void getMapping() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    protected void restController() {
    }
}
