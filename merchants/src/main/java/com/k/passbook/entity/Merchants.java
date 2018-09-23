package com.k.passbook.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>商户对象模型</h1>
 * '@Entity'//表明是一个实体对象
 * '@Table'//实体对象属性方面的定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {


    /**
     * 商户 id, 主键
     * '@id' 表明这个属性时 merchants 表的主键
     * '@GeneratedValue' ：插入属性值得时候不用去配置，会自动生成保存在数据库
     * '@Column' 列
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;


    /**
     * 商户名称，需要时全局唯一的
     * '@Basic' 注解表明这是merchants表的一个基本列，如果不加的话，效果是一样的，默认对象里的每一个属性都是basic
     */
    @Basic
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * 商户logo
     */
    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    /**
     * 商户营业执照
     */
    @Basic
    @Column(name = "business_license_url", nullable = false)
    private String businessLicenseUrl;

    /**
     * 商户的联系电话
     */
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 商户地址
     */
    @Basic
    @Column(name = "address", nullable = false)
    private String address;


    /**
     * 商户是否通过审核的标志位
     */
    @Basic
    @Column(name = "is_audit", nullable = false)
    private Boolean isAudit = false;
}
