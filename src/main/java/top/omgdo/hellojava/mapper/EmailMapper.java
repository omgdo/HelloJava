package top.omgdo.hellojava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.omgdo.hellojava.entity.Email;

import java.util.List;

@Repository
public interface EmailMapper extends BaseMapper<Email> {

    /**
     * 当不存在时，创建表
     * @param tableName 表名
     */
    @Insert("create table IF NOT EXISTS ${tableName} (username varchar(255),password varchar(255))")
    void createTable(String tableName);

    @Insert("insert into ${tableName} values(#{username},#{password})")
    void insert1(String tableName, String username, String password);


    void insertForeach(String tableName, List<Email> emails);
}
