package top.omgdo.hellojava.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor

@TableName("email")
public class Email {
    private String username;
    private String password;
}
