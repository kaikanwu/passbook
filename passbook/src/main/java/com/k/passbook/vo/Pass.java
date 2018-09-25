package com.k.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>用户领取的优惠券</h1>
 * 用户领取的优惠券被称为pass
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pass {

    /** 用户id */
    private Long userId;

    /** pass 在HBase 中的 RowKey */
    private String rowKey;

    /** passtemplate 在HBase 中的 RowKey */
    private String templateId;

    /** 优惠券的token, 有可能是 null， 则填充 -1 */
    private String token;

    /** 领取日期*/
    private Date assignedDate;

    /** 消费日期， 不为空代表已经被消费了 */
    private Date conDate;



}
