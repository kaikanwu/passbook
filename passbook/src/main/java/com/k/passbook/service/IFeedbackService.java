package com.k.passbook.service;

import com.k.passbook.vo.Feedback;
import com.k.passbook.vo.Response;

/**
 * <h1>用户评论功能的接口</h1>
 */
public interface IFeedbackService {

    /**
     * <h2> 创建评论 </h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response createFeedback(Feedback feedback);

    /**
     * <h2> 获取用户评论 </h2>
     * @param userId  用户 id
     * @return {@link Response}
     */
    Response getFeedback(Long userId);
}
