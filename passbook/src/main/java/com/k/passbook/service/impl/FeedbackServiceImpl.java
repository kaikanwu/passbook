package com.k.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.k.passbook.constant.Constants;
import com.k.passbook.mapper.FeedbackRowMapper;
import com.k.passbook.service.IFeedbackService;
import com.k.passbook.utils.RowKeyGenUtil;
import com.k.passbook.vo.Feedback;
import com.k.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>评论功能实现</h1>
 * @author  kk
 */
@Slf4j
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    /**
     * HBase 客户端
     *
     * 由于创建评论和获得评论都需要HBase, 所以先创建HBase 客户端
     */
    private final HbaseTemplate hbaseTemplate;

    @Autowired
    public FeedbackServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }


    @Override
    public Response createFeedback(Feedback feedback) {


        /**
         * 先校验 feedback 是否有效
         */
        if (!feedback.validate()) {
            log.error("Feedback Error : {}", JSON.toJSONString(feedback));
            return Response.failure("Feedback Error");
        }

        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genFeedbackRowKey(feedback)));

        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.USER_ID),
                Bytes.toBytes(feedback.getUserId())
        );
        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.TYPE),
                Bytes.toBytes(feedback.getType())
        );
        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.TEMPLATE_ID),
                Bytes.toBytes(feedback.getTemplateId())
        );
        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.COMMENT),
                Bytes.toBytes(feedback.getComment())
        );

        hbaseTemplate.saveOrUpdate(Constants.Feedback.TABLE_NAME, put);

        return Response.success();
    }


    @Override
    public Response getFeedback(Long userId) {

        byte[] reverseUserId = new StringBuilder(String.valueOf(userId)).reverse().toString().getBytes();
        Scan scan = new Scan();
        /** 过滤器 */
        scan.setFilter(new PrefixFilter(reverseUserId));

        /**
         * HBase 中的 find 可以获取多个记录，如果没有会返回一个空的
         *            get 则只能获得一条记录，如果没有回抛出异常
         */
        List<Feedback> feedbacks = hbaseTemplate.find(Constants.Feedback.TABLE_NAME, scan, new FeedbackRowMapper());

        return new Response(feedbacks);
    }
}
