package top.omgdo.hellojava.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.omgdo.hellojava.entity.Email;
import top.omgdo.hellojava.entity.User;
import top.omgdo.hellojava.mapper.EmailMapper;
import top.omgdo.hellojava.service.impl.EmailServiceImpl;
import top.omgdo.hellojava.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {



    @Autowired
    UserServiceImpl userService;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    EmailMapper emailMapper;



    @GetMapping("/insert")
    public String insert(){

        new Run(0,4,emailMapper).start();
        new Run(4,4,emailMapper).start();
        new Run(8,4,emailMapper).start();
        new Run(12,4,emailMapper).start();
        new Run(16,4,emailMapper).start();
        new Run(20,4,emailMapper).start();
        new Run(24,4,emailMapper).start();
        new Run(28,4,emailMapper).start();
        new Run(32,4,emailMapper).start();


        return "成功";
    }



    @GetMapping("/delete")
    public String delete(){
        emailService.remove(null);
        return "成功";
    }

    @GetMapping("/create/{table}")
    public String create(@PathVariable String table){
        emailService.create(table);
        return "成功";
    }


    
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }


    @SneakyThrows
    @RequestMapping("/login")
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


@Slf4j
class Run extends Thread{

    private EmailMapper emailMapper;
    private File [] files;
    private int index;
    private int size;
    private String tablePreName = "email_";

    public Run(int index,int size,EmailMapper emailMapper) {
        this.index = index;
        this.size = size;
        this.emailMapper = emailMapper;

        String url = "F:\\Thunder Network\\迅雷下载\\BreachCompilation\\data\\";

        File file = new File(url);

        this.files = file.listFiles();
    }



//    List<String> list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
//            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
//

    @Override
    public void run() {

        int range = index + size;

        int idx = index;

        while (idx < range){

            File file = files[idx];

            String tableName = tablePreName + file.getName();
            //创建表名
            emailMapper.createTable(tableName);
            if(file.isDirectory()){
                getList(file,tableName);
            }

            idx ++ ;
        }

    }


    @SneakyThrows
    public List<Email> getList(File file,String tableName){
        
//        遍历所有文件
        for (File listFile : file.listFiles()) {


//            判断是不是目录，是目录递归查找
            if (listFile.isDirectory()){
                log.info("进入 {} 目录中...." , listFile);
                getList(listFile,tableName);
            }

//            判断是不是文件，是文件写入操作
            if(listFile.isFile()){

                log.info("进入 {} 文件中,执行读写操作..." ,listFile);
                InputStreamReader reader = new InputStreamReader(new FileInputStream(listFile.getAbsolutePath()));
                BufferedReader buf = new BufferedReader(reader);
                String line;
                int index = 0;
                ArrayList<Email> list = new ArrayList<>(500);
                while ((line = buf.readLine())!=null){

                    Email email = new Email();

                    int i = line.indexOf(":");
                    if(i==-1)
                        email.setUsername(line);

                    else
                    {
                        email.setUsername(line.substring(0,i));
                        email.setPassword(line.substring(i+1));
                    }

                    list.add(email);
                    index ++;
                    if(index >= 500)
                    {
                        index = 0;
                        try{
                            emailMapper.insertForeach(tableName,list);
                        }catch (Exception e){
                            log.error("e.getMessage() = " + e.getMessage());
                        }


                        list = new ArrayList<>(500);
                    }
                }
                log.info("{} 文件,完成数据库写入..." ,listFile);
            }
        }


        return null;
    }

    public static void main(String[] args) {

        String url = "F:\\Thunder Network\\迅雷下载\\BreachCompilation\\data\\";

        File file = new File(url);

        for (File listFile : file.listFiles()) {


        }

    }
}