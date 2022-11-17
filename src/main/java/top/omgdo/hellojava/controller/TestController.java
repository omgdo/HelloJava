package top.omgdo.hellojava.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.omgdo.hellojava.entity.User;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/point")
    public String point(){
        String t= null;
        t.trim();
        return "point";
    }
    
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }


    @SneakyThrows
    @PostMapping("/login")
    public String login(@RequestBody @Validated User user){

        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = managerFactory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);


        return "登录成功";
    }
}
