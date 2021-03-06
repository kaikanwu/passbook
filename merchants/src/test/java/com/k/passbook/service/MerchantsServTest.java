package com.k.passbook.service;

import com.alibaba.fastjson.JSON;
import com.k.passbook.vo.CreateMerchantsRequest;
import com.k.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <h1> 商户服务测试类</h1>
 * @author kk
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {

    @Autowired
    private IMerchantsServ merchantsServ;


    /**
     * '@Transactional' 可以实现数据库的自动回滚（自动删除测试用例生成的数据）
     */
    @Test
//    @Transactional
    public void testCreateMerchantsServ() {
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("google");
        request.setLogoUrl("www.google.com");
        request.setBusinessLicenseUrl("www.google.com");
        request.setPhone("123456");
        request.setAddress("Mountain View, CA");

        System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
    }


    @Test
    public void testBuildMerchantsInfoById() {
        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(22)));
    }

    @Test
    public void testDropPassTemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(22);
        passTemplate.setTitle("title: google");
        passTemplate.setSummary("简介：google");
        passTemplate.setDesc("详情： google");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackground(2);
        passTemplate.setStart(new Date());
        passTemplate.setEnd(DateUtils.addDays(new Date(), 20));

        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));
    }

}
