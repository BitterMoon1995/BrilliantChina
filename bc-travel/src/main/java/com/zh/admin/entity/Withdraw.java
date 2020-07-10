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
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Withdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer amount;

    private String wechatId;

    /**
     * 申请时间（提现工单创建时间）
     */
    private LocalDateTime createTime;

    /**
     * 提现状态：0.未处理 1.已处理 2.有问题
     */
    private String state;


}
