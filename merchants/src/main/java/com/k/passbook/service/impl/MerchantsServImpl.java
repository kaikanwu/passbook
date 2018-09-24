package com.k.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.k.passbook.constant.Constants;
import com.k.passbook.constant.ErrorCode;
import com.k.passbook.dao.MerchantsDao;
import com.k.passbook.entity.Merchants;
import com.k.passbook.service.IMerchantsServ;
import com.k.passbook.vo.CreateMerchantsRequest;
import com.k.passbook.vo.CreateMerchantsResponse;
import com.k.passbook.vo.PassTemplate;
import com.k.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <h1>商户服务接口的实现</h1>
 * 每个service的实现都应该包含这两个注解: 'Slf4j' -> log,'Service'-> java bean
 * @author kk
 */
@Slf4j
@Service
public class MerchantsServImpl  implements IMerchantsServ {

    /** Merchants 数据库接口*/
    private final MerchantsDao merchantsDao;

    /** kafka 客户端*/
    private final KafkaTemplate<String ,String > kafkaTemplate;

    /**
     * 以构造函数的方式注入
     * @param merchantsDao
     */
    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 接受一个请求，判断后，创建商户对象
     * @param request {@link CreateMerchantsRequest}创建商户的请求
     * @return
     */
    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {

        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        ErrorCode errorCode = request.validate(merchantsDao);
        //如果错误码返回的不是SUCCESS，则将响应设置为对应的错误信息
        if (errorCode != ErrorCode.SUCCESS){
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else {
            //若返回的是SUCCESS，则将请求对象转换为Merchants对象存入数据库中。
            //merchantsResponse 用来给商户设置id
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }

        response.setData(merchantsResponse);


        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Merchants merchants = merchantsDao.findById(id);
        if (merchants == null) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());

        }
        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        //验证是否已经商户注册
        ErrorCode errorCode = template.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else {
            String passTemplate = JSON.toJSONString(template);
            //send 方法的三个参数， String topic, String key, String data
            //这里的key 都设置为同一个值， TEMPLATE_TOPIC = "merchants-template"
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplate:{}" , passTemplate);
        }

        return response;
    }
}
