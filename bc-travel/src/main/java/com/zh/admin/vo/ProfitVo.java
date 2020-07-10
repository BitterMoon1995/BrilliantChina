package com.zh.admin.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProfitVo {
    Integer levelOnes;
    Integer levelTwos;
    Integer profit;
}
