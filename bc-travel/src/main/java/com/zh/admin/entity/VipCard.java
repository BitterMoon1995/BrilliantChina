package com.zh.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author God周周神
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VipCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String openid;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 真名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 真人照片
     */
    private String photoSrc;

    /**
     * 6位推广码
     */
    private String promoCode;

    /**
     * 上级ID
     */
    private String superiorId;

    /**
     * 办卡/续卡时间
     */
    private Date createTime;

    /**
     * 剩余会员天数
     */
    private Integer remainingDays;

    private Integer gender;

    private Date birthday;
}
