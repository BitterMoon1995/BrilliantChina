package starry.evenstar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable,Comparable<User> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    //1：超级管理员   2：管理员   3：客户
    private Integer role;

    private String phone;

    private String company;

    private String email;

    private boolean activated;

    //周神将返回的UserList按照role排序，官大的在前，官一样大，按照首字母升序
    // 方案是实现compareTo接口

    @Override
    public int compareTo(User o) {
        int i = this.getRole()-o.getRole();
        if (i!=0) return i;
        else {
            char c = this.getUsername().charAt(0);
            char c0 = o.getUsername().charAt(0);
            return c-c0;
        }
    }
}
