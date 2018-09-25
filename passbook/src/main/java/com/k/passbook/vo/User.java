package com.k.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> User object</h1>
 *
 * 这个对象里的定义和 HBase 里 User 表格的信息相对应
 */
public class User {

    /** 用户 id */
    private Long id;
    /** 用户基本信息 */
    private BaseInfo baseInfo;
    /** 用户额外信息 */
    private OtherInfo otherInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseInfo{

        private String name;
        private Integer age;
        private String sex;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherInfo{
        private String phone;
        private String address;
    }
}
