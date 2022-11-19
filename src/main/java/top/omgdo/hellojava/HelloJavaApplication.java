package top.omgdo.hellojava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class HelloJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloJavaApplication.class, args);
    }

}
