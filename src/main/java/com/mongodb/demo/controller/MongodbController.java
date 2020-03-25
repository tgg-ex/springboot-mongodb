package com.mongodb.demo.controller;

import com.mongodb.demo.model.Test;
import com.mongodb.demo.service.IMongodbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zeng
 * <p>
 * crud mongdb
 */
@RestController
@RequestMapping("/crud")
@Slf4j
public class MongodbController {

    @Autowired
    private IMongodbService iMongodbService;

    @RequestMapping("/add")
    public String add() {
        Test test = new Test();
        test.setId(UUID.randomUUID().toString());
        test.setPrice((int) (Math.random() * 1000));
        test.setName("张" + (int) (Math.random() * 1000));
        test.setPublish("hhh");
        test.setCreateTime(new Date());
        test.setUpdateTime(new Date());
        iMongodbService.add(test);
        return "ok";
    }

    @RequestMapping("/list")
    public List<Test> list() {
        List<Test> list = iMongodbService.list();
        log.info("list{}" + list);
        return list;
    }

    @RequestMapping("/findLink")
    public List<Test> findLink(@RequestParam("name") String name) {
        Test testWhere = new Test();
        testWhere.setName(name);
        return iMongodbService.findLink(testWhere);
    }

    @RequestMapping("/listWhere")
    public List<Test> listWhere(@RequestParam("id") String id) {
        Test testWhere = new Test();
        testWhere.setId(id);
        return iMongodbService.listWhere(testWhere);
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") String id) {
        Test testWhere = new Test();
        testWhere.setId(id);
        iMongodbService.update(testWhere);
        return "ok";
    }

    @RequestMapping("/del")
    public String del(@RequestParam("name") String name) {
        Test testWhere = new Test();
        testWhere.setName(name);
        iMongodbService.del(testWhere);
        return "ok";
    }
}
