package com.k.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.k.passbook.service.IMerchantsServ;
import com.k.passbook.vo.CreateMerchantsRequest;
import com.k.passbook.vo.PassTemplate;
import com.k.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务 controller </h1>
 * @author kk
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {
    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }
    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {

        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    /**
     *
     * @param id id 作为一个动态变量作为url中的一部分
     * @return
     */
    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id) {

        log.info("BuildMerchantsInfo: {}", id );
        return merchantsServ.buildMerchantsInfoById(id);
    }


    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {

        log.info("DropPassTemplate: {}" , passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }




}
