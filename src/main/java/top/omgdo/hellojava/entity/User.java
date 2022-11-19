package top.omgdo.hellojava.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6,message = "用户名不能小于6位")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8,message = "密码不能小于8位")
    private String password;

    private String salt;
}
