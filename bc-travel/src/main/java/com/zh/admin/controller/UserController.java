package com.zh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.admin.entity.User;
import com.zh.admin.service.IUserService;
import com.zh.admin.vo.UserVo;
import com.zh.common.Info;
import com.zh.common.Result;
import com.zh.common.iResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/evenstar/user")
public class UserController {
    @Autowired
    IUserService service;
    @Autowired
    RedisTemplate<String,String> template;
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
                ValueOperations<String, String> op = template.opsForValue();
                //Redis存入token-username对，设置120分钟的过期时间
                op.set(user.getUsername(),uuid.toString(),60*120, TimeUnit.SECONDS);
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
            pagedSearch(pageNum, pageSize, vo, wrapper);
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

    //普通管理员只能查到客户
    @GetMapping("/getClients")
    public UserVo getClients(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition){
        UserVo vo = new UserVo();
        //非搜索请求
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (condition.isEmpty()){
            wrapper.eq("role",3);
            Page<User> page = new Page<>(pageNum, pageSize,false);//第几页 每一页几个 不计数
            Page<User> userPage = service.page(page,wrapper);
            List<User> userList = userPage.getRecords();
            vo.setUserList(userList);
            vo.setTotal(service.count());
        }
        //搜索请求，condition是条件。这里根据用户名搜索
        else {
            wrapper.like("username",condition).eq("role",3);
            pagedSearch(pageNum, pageSize, vo, wrapper);
        }
        return vo;
    }

    private void pagedSearch(@RequestParam Integer pageNum, @RequestParam Integer pageSize, UserVo vo, QueryWrapper<User> wrapper) {
        Page<User> page = new Page<>(pageNum, pageSize,false);
        List<User> userList = service.page(page, wrapper).getRecords();
        Collections.sort(userList);
        vo.setUserList(userList);
        vo.setTotal(service.count(wrapper));
    }

    @DeleteMapping("/logout")
    public iResult logout(@RequestParam String username){
        boolean delete = template.delete(username);
        if (delete)
            return iResult.success;
        else return iResult.serverDown;
    }
}
