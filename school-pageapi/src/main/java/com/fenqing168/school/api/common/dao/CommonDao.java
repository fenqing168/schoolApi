package com.fenqing168.school.api.common.dao;

import com.fenqing168.school.api.pojo.UserEntity;

/**
 * 公共dao
 * @param <T>
 */
public interface CommonDao<T> {

    /**
     * 通过Javabean查询对象
     * @param t
     * @return
     */
    T queryObjectByBean(T t);

    int insert(T t);
}
