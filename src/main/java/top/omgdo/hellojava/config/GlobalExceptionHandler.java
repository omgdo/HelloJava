package top.omgdo.hellojava.config;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



@Configuration
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointException(NullPointerException e){
        return "空指针异常";
    }

    @ExceptionHandler(UnknownAccountException.class)
    public String unknownAccountException(UnknownAccountException e){
        return "没有此用户!";
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public String incorrectCredentialsException(IncorrectCredentialsException e){
        return "密码错误!";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidException(MethodArgumentNotValidException e){
        return e.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage();
    }
}
