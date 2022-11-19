package top.omgdo.hellojava.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.omgdo.hellojava.entity.User;
import top.omgdo.hellojava.service.UserService;

public class MyRealm extends AuthorizingRealm {


    @Autowired
    protected UserService userService;
//    用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

//    身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        用户名
        String principal = (String) token.getPrincipal();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",principal);

        User one = userService.getOne(wrapper);
        if(one != null)
        {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), one.getPassword(), ByteSource.Util.bytes(one.getSalt()), principal);
            return info;
        }
        return null;
    }
}
