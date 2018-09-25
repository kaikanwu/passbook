package com.k.passbook.vo;

import com.google.common.base.Enums;
import com.k.passbook.constant.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * <h1> 用户评论 </h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    /** 用户 id */
    private Long userId;

    /** 评论类型 */
    private String type;

    /** PassTemplate RowKey, 如果是 app 类型的评论，则没有 */
    private String templateId;

    /** 评论具体内容 */
    private String comment;


    /**
     * 校验 feedback type
     * @return
     */
    public boolean validate() {
        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class, this.type.toUpperCase()
        ).orNull();

        //机制是返回的类型不能为空，评论内容也不能为空。 两者都不为空才返回 true
        return !(null == feedbackType || null == comment);
    }

}
