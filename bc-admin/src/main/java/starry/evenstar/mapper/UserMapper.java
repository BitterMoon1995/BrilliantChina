package starry.evenstar.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import starry.evenstar.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("ALTER TABLE `user` ORDER BY role,username")
    void resetOrder();
}
