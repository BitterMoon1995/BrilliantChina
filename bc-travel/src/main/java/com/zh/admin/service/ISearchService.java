package com.zh.admin.service;

import com.zh.admin.entity.Search;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author God周周神
 * @since 2020-07-16
 */
public interface ISearchService extends IService<Search> {
    void addRecentSearch(String openid,String content);
    //删除最早的搜索记录
    void delEarliest(String openid);
}
