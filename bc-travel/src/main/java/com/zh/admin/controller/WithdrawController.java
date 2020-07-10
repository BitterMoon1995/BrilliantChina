package com.zh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.admin.entity.Withdraw;
import com.zh.admin.service.IWithdrawService;
import com.zh.admin.vo.WithdrawVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import util.QueryParams;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author God周周神
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/admin/withdraw")
public class WithdrawController {
    @Autowired
    IWithdrawService service;

    @PostMapping("/getList")
    public WithdrawVo getList(@RequestBody QueryParams queryParams){
        System.out.println(queryParams);

        WithdrawVo vo = new WithdrawVo();
        QueryWrapper<Withdraw> wrapper = new QueryWrapper<>();
        wrapper.eq("state","未处理").or().eq("state","处理中")
                .orderByAsc("create_time");
        List<String> conditions = queryParams.getConditions();
        Integer pageNum = queryParams.getPageNum();
        Integer pageSize = queryParams.getPageSize();
        Page<Withdraw> page = new Page<>(pageNum, pageSize);

        //非搜索
        if (conditions != null && conditions.size() != 0) {
            String wechatId = conditions.get(0);
            wrapper.like("wechat_id", wechatId);
        }
        vo.setList(service.page(page,wrapper).getRecords());
        vo.setTotal(service.count());
        return vo;
    }

    @PostMapping("/change")
    public Integer change(@RequestBody Withdraw withdraw){
        service.updateById(withdraw);
        return 0;
    }

}
