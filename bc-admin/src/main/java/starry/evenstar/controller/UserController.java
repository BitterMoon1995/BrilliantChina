package starry.evenstar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import starry.evenstar.entity.User;
import starry.evenstar.service.IUserService;
import starry.evenstar.vo.UserVo;
import util.Info;
import util.Result;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/evenstar/user")
public class UserController {
    @Autowired
    IUserService service;
    //登陆
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Result result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        List<User> list = service.list(wrapper);
        if (list.size()==0) {
            result.info= new Info("用户名不存在！", 404);
        }
        else {
            User user1 = list.get(0);
            if (!user.getPassword().equals(user1.getPassword())){
                result.info= new Info("密码错误！", 400);
            }
            else {
                UUID uuid = UUID.randomUUID();
                result.data= String.valueOf(uuid);
                result.info= new Info("登录成功！", 200);
            }
        }
        return result;

    }

    //分页查询所有用户
    //分页需要总条目数witch is extremely important
    @GetMapping("/getAll")
    public UserVo getAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition){
        UserVo vo = new UserVo();
        //非搜索请求
        if (condition.isEmpty()){
            Page<User> page = new Page<>(pageNum, pageSize,false);//第几页 每一页几个 不计数
            Page<User> userPage = service.page(page);
            List<User> userList = userPage.getRecords();
            vo.setUserList(userList);
            vo.setTotal(service.count());
        }
        //搜索请求，condition是条件。这里根据用户名搜索
        else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.like("username",condition);
            Page<User> page = new Page<>(pageNum, pageSize,false);
            List<User> userList = service.page(page, wrapper).getRecords();
            Collections.sort(userList);
            vo.setUserList(userList);
            vo.setTotal(service.count(wrapper));
        }
        return vo;
    }

    //周神为了实现每一次完美的分页&排序(先order再username)查询，自写SQL，顺利完成
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    //改
    @PostMapping("/update")
    public User update(@RequestBody User user){
        service.updateById(user);
        return service.getById(user.getId());
    }

    //增
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        Result result = new Result(new Object(),new Info());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        List<User> list = service.list(wrapper);
        if (list.isEmpty()){
            service.save(user);
            //如果不返数据，要将data置空返回去，不然要报500 no serializer（不会影响业务）
            //因为Object未实现那个接口，原则上不能参与数据传输
            result.data=null;
            result.info.setMsg("添加用户成功！");
            result.info.setCode(200);
        }
        else {
            result.data=null;
            result.info.setMsg("用户已存在！");
            result.info.setCode(400);
        }
        return result;
    }
    //删
    @DeleteMapping("/delOne")
    public Boolean delOne(@RequestParam String id){
        return service.removeById(id);
    }
}
