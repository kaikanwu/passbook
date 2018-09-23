package com.k.passbook.dao;

import com.k.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>Merchants Dao 接口</h1>
 * Jpa: 运用java对象，给我们提供一些便捷的方法，
 * 我们只需要定义一些接口，在运行的时候，jpa会自动生成一些sql 语句
 * JpaRepository<需要对应那张表, 表主键的类型>
 */
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    /**
     * <h2>根据 id 获取商户对象</h2>
     * @param id 商户的id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);


    /**
     * <h2> 根据商户名称获取商户对象</h2>
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);
}
