package com.k.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户响应对象</h1>
 * 返回告诉商户你在数据库里创建的 merchants 对象 id 是什么
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {

    /** 商户id : 创建失败则为-1 */
    private Integer id;

}
