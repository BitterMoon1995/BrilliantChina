package starry.evenstar.vo;

import lombok.Data;
import starry.evenstar.entity.User;

import java.util.List;
//分页查询传输数据对象
@Data
public class UserVo {
    List<User> userList;
    Integer total;
}
/*
2020/5/7
   分页查询，为了不加这个total，不新开vo这个包，不新增UserVo这个类，前端部分绞尽脑汁，耗了一下午，依然有小BUG
后端直接return倒确实爽了，但是前端代码的复杂度直接呈指数级上升，变为原来的130倍。而且还是错的
 */