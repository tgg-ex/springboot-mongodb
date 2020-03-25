package com.mongodb.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author zeng
 * mongodb 实体
 */
//文档名称 类似mysql表名称
@Document("test")
@Data
public class Test {

    @Id
    private String id;
    private Integer price;
    private String name;
    private String info;
    private String publish;
    private Date createTime;
    private Date updateTime;
}
