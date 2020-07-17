package com.zh.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author God周周神
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 搜索内容
     */
    private String content;

    /**
     * 搜索者用户id
     */
    private String searcher;

    /**
     * 搜索时间。用户只保留最近5次搜索
     */
    private LocalDateTime searchTime;

    /**
     * 是否是热搜
     */
    private Integer hot;

    /**
     * 热搜顺序
     */
    private Integer orderNum;

    /**
     * 买热搜的商家id
     */
    private String owner;


}
