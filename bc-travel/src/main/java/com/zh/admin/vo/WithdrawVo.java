package com.zh.admin.vo;

import com.zh.admin.entity.Withdraw;
import lombok.Data;

import java.util.List;

@Data
public class WithdrawVo {
    List<Withdraw> list;
    Integer total;
}
