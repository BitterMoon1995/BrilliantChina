package starry.evenstar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import starry.evenstar.entity.User;
import starry.evenstar.mapper.UserMapper;
import starry.evenstar.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper mapper;

    @Override
    public void resetOrder() {
        mapper.resetOrder();
    }
}
