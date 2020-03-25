package com.mongodb.demo.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.demo.model.Test;
import com.mongodb.demo.service.IMongodbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zeng
 * <p>
 * crud实现类
 */
@Service
@Slf4j
public class MongodbServiceImpl implements IMongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(Test test) {
        mongoTemplate.save(test);
    }

    @Override
    public List<Test> list() {
        return mongoTemplate.findAll(Test.class);
    }

    @Override
    public List<Test> findLink(Test test) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("name").regex("^.*" + test.getName() + ".*$");
        query.addCriteria(criteria);
//        List<Test> testList = mongoTemplate.find(new Query(where("name").regex("^.*" + test.getName() + ".*$")), Test.class);
        List<Test> testList = mongoTemplate.find(new Query(where("name").regex("^.*" + test.getName() + ".*$")), Test.class);
        Long count = mongoTemplate.count(query, Test.class);
        System.out.println("count：" + count);
        return testList;
    }

    @Override
    public List<Test> listWhere(Test test) {
        return mongoTemplate.find(new Query(where("id").is(test.getId())), Test.class);
    }

    @Override
    public void update(Test test) {
        //使用更新的文档更新所有与查询文档条件匹配的对象
        UpdateResult updateResult = mongoTemplate.updateMulti(new Query(where("id").is(test.getId())), new Update().set("name", "pppp"), Test.class);
        log.info("update id:{}" + updateResult.getUpsertedId());

    }

    @Override
    public void del(Test test) {
        mongoTemplate.remove(new Query(where("name").is(test.getName())), Test.class);
    }
}
