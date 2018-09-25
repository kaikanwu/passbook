package com.k.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>投放的优惠券对象定义</h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplate {
    /** 所属商户 id */
    private Integer id;

    /** 优惠券标题 id */
    private String title;

    /** 优惠券摘要 */
    private String summary;

    /** 优惠券描述 */
    private String desc;

    /** 最大个数限制 */
    private Long limit;

    /** 优惠券是否有token, 用商户核销 */
    private Boolean hasToken;

    /** 优惠券背景色 */
    private Integer background;

    /** 优惠券开始时间 */
    private Date start;

    /** 优惠券结束时间 */
    private Date end;

}
