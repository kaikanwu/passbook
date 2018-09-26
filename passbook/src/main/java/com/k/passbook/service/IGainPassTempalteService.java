package com.k.passbook.service;

import com.k.passbook.vo.GainPassTemplateRequest;
import com.k.passbook.vo.Response;

/**
 * <h1> 用户领取优惠券功能实现 </h1>
 */
public interface IGainPassTempalteService {

    /**
     * <h2> 用户领取优惠券</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;

}
