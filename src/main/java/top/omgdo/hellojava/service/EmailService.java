package top.omgdo.hellojava.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.omgdo.hellojava.entity.Email;


public interface EmailService extends IService<Email> {

    void create(String tableName);
}
