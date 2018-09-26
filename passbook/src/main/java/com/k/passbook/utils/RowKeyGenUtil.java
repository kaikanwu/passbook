package com.k.passbook.utils;

import com.k.passbook.vo.Feedback;
import com.k.passbook.vo.PassTemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1> RowKey 生成器工具类</h1>
 */
@Slf4j
public class RowKeyGenUtil {

    /**
     * <h2>根据提供的 PassTemplate 对象 生成其 RowKey， 并返回 </h2>
     * @param passTemplate {@link PassTemplate}
     * @return String RowKey
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate) {

        /** 由于定义的PassTemplate 的 Title 是唯一的， 所以这里拼接成的 passInfo 也是唯一的 */
        String passInfo = String.valueOf(passTemplate.getId()) + "_" + passTemplate.getTitle();

        String rowKey = DigestUtils.md5Hex(passInfo);

        /** 使用 log 前， 需要使用 '@Slf4j' */
        log.info("GenPassTemplateRowKey: {}, {} ", passInfo, rowKey);

        return rowKey;
    }


    /**
     * <h2> 根据 Feedback 构造 RowKey</h2>
     * @param feedback {@link Feedback}
     * @return String RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback) {

        /**
         * 'String.valueOf(feedback.getUserId())).reverse().toString()'
         * 一个用户可以获取他所有的评论，
         * 所以一个用户评论放在相近的地方比较好（HBase 特性），方便做一个扫描的操作。
         *
         * 存储的userID 是根据用户数来增加的，所以前面的几位数是相同的，而后面的几位数则是随机的。
         * 所以使用 .reverse() 翻转 userId 来获取随机数开头， 越随机，数据越分散。
         * 这样可以将不同用户的数据，分散到不同的机器上。
         *
         * '(Long.MAX_VALUE - System.currentTimeMillis())'
         * System.currentTimeMillis() =》 用户越晚创建，这个时间戳的时间越大
         * 用一个大的数减去一个越大的数，则得到一个越小的数。 所以越晚创建的评论，这部分的值越小。
         * 最后HBase中，越靠前面的，说明是越晚创建的。方便后面的扫描和使用。
         */
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString() +
                (Long.MAX_VALUE - System.currentTimeMillis());


    }


}
