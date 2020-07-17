package com.zh.admin.service.impl;

import com.zh.admin.entity.Search;
import com.zh.admin.mapper.SearchMapper;
import com.zh.admin.service.ISearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author God周周神
 * @since 2020-07-16
 */
@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements ISearchService {
    @Autowired
    SearchMapper mapper;
    @Override
    public void addRecentSearch(String openid,String content) {
        Search search = new Search();
        search.setSearcher(openid);
        search.setContent(content);
        search.setSearchTime(LocalDateTime.now());
        save(search);
    }

    @Override
    public void delEarliest(String openid) {
        mapper.delEarliest(openid);
    }
}
