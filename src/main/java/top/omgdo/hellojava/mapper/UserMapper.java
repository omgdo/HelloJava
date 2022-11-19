package top.omgdo.hellojava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.omgdo.hellojava.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
