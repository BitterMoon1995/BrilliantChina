package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.admin.entity.User;
import com.zh.admin.service.IUserService;
import com.zh.common.Info;
import com.zh.common.Result;
import com.zh.mini.bo.SearchResult;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.zh.mini.service.ISliderService;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.vo.DetailPage;
import com.zh.mini.vo.StickyObjectVo;
import com.zh.mini.vo.SceneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@RestController
@CrossOrigin
@RequestMapping("/mini/scene")
public class SceneController {
    @Autowired
    ISceneService service;
    @Autowired
    IUserService userService;
    @Autowired
    ISceneImageService imageService;
    //周神之：归来

    @GetMapping("/getByUsername")
    public SceneVo getByUsername(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition , @RequestParam String username){
        SceneVo sceneVo = new SceneVo();
        Page<Scene> page = new Page<>(pageNum,pageSize);
        //获取用户
        User user = getUser(username);
        //非搜索
        if (condition.isEmpty()) {
            //客户只能查自己的项目
            if (user.getRole() == 3) {
                QueryWrapper<Scene> wrapper = new QueryWrapper<>();
                wrapper.eq("username", username);
                List<Scene> list = service.page(page,wrapper).getRecords();
                sceneVo.setSceneList(list);
                //一定要返回真正的总数量
                sceneVo.setTotal(service.count(wrapper));
            //管理员或维尼，返回所有
            } else {
                List<Scene> list = service.page(page).getRecords();
                sceneVo.setSceneList(list);
                sceneVo.setTotal(service.count());
            }
        }
        //搜索
        else {
            //非管理员根据username查
            List<Scene> sceneList;
            if (!username.equals("admin") && !username.equals("manager")) {
                sceneList = service.search(username,condition);
                //一定要返回真正的总数量
            } else {
                sceneList = service.allSearch(condition);
            }
            sceneVo.setSceneList(sceneList);
            sceneVo.setTotal(sceneList.size());
        }
        return sceneVo;
    }
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    @Transactional
    @PostMapping("/add")
    public Result add(@RequestBody Scene scene){
        Result result = new Result(new Object(),new Info());
        result.setData(null);
        String name = scene.getName();
        QueryWrapper<Scene> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Scene> list = service.list(wrapper);
//        ID为空 说明是新增
        if (scene.getId()==null){
//            重名，报错
            if (list.size()!=0){
                result.info.setCode(400);
                result.info.setMsg("景区名已存在！");
                return result;
            }
            //设置创建时间
            scene.setCreateTime(new Date());
            service.add(scene);
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
        }
//        ID不为空就是修改，修改不检查重名！！！！！！！
        else {
            Integer edit = service.edit(scene);
            if (edit == 400){
                result.info.setCode(400);
                result.info.setMsg("景区名已存在！");
            }
            else {
                result.info.setCode(200);
                result.info.setMsg("编辑景区成功！");
            }
        }
        service.resetOrder();
        return result;
    }

    @GetMapping("/getScene")
    public Scene get(@RequestParam String id,@RequestParam boolean img){
        Scene scene = service.getById(id);
        if (img)
        service.setImgs(scene,id);
        return scene;
    }
//    因为公司在服务器租赁上的预算较少，服务器的总硬盘容量可能只有50G
//    所以所有的删除操作都采取物理删除
    @DeleteMapping("/del")
    public boolean delScene(@RequestParam String id){
        service.delDetails(id);
        return service.removeById(id);
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String condition){
        return service.search(condition);
    }

    @GetMapping("/showList")
    public List<SearchResult> showList(){
        return service.showList();
    }

    //传输详情页VO。意义：减少WEB层请求次数，降低服务器带宽压力
    @GetMapping("/getById")
    public DetailPage getById(String Id){
        Scene scene = service.getById(Id);

        QueryWrapper<SceneImage> query = new QueryWrapper<>();
        query.eq("scene_id",Id).eq("type","intros").orderByAsc("order_num");
        List<SceneImage> intros = imageService.list(query);

        query.clear();
        query.eq("scene_id",Id).eq("type","richText");
        SceneImage richText = imageService.getOne(query);

        //不太优雅
        return new DetailPage(intros,scene.getName(),scene.getLocation(),scene.getLevel(),
                scene.getPrice(),scene.getLongitude(),scene.getLatitude(),richText);
    }

    //根据用户名返回用户
    public User getUser(String username){
        QueryWrapper<User> userQ = new QueryWrapper<>();
        userQ.eq("username",username);
        return userService.getOne(userQ);
    }
}
