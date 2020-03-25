package com.mongodb.demo.service;

import com.mongodb.demo.model.Test;

import java.util.List;

/**
 * @author zeng
 * <p>
 * crud 业务接口
 */
public interface IMongodbService {

    void add(Test test);

    List<Test> list();

    List<Test> findLink(Test test);

    List<Test> listWhere(Test test);

    void update(Test test);

    void del(Test test);
}
