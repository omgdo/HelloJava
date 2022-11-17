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
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody String body){

        JSONObject object = new JSONObject(body);
        String username = object.getString("username");
        String password = object.getString("password");

        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = managerFactory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        subject.login(token);


        return "登录成功";
    }
}
