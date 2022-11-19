package top.omgdo.hellojava.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.omgdo.hellojava.entity.Email;

import top.omgdo.hellojava.mapper.EmailMapper;

import top.omgdo.hellojava.service.EmailService;



@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {
    @Override
    public void create(String tableName) {
        baseMapper.createTable(tableName);
    }
}
