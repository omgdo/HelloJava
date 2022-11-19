package top.omgdo.hellojava.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.omgdo.hellojava.entity.User;
import top.omgdo.hellojava.mapper.UserMapper;
import top.omgdo.hellojava.service.UserService;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
