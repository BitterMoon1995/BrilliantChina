package starry.evenstar.service;

import org.apache.ibatis.annotations.Select;
import starry.evenstar.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
public interface IUserService extends IService<User> {
    void resetOrder();

}
