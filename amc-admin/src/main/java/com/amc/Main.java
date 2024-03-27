package com.amc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amc.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        System.out.println("Amc 服务启动成功");

    }
}

/**
 * 前端监控平台表结构设计
 * 1. 项目表
 *      项目id    项目名称    项目描述    项目创建时间
 * 2. 用户表 一个项目有多个用户存在
 *      用户id    用户名       项目id
 * 3. 性能监控表
 *      性能监控id    项目id    用户id    页面地址    sdk版本    上报时间    用户id    会话标识    性能指标值    设备信息    apiKey
 */
/**
 * 帮我生成上面定义的表结构的sql语句
 * 1. 项目表
 *     create table project(
 *     project_id int primary key auto_increment,
 *     project_name varchar(255),
 *     project_desc varchar(255),
 *     create_time timestamp
 *     );
 * 2. 用户表
 *    create table user(
 *    user_id int primary key auto_increment,
 *    user_name varchar(255),
 *    project_id int
 *    );
 *    alter table user add constraint fk_project_id foreign key(project_id) references project(project_id);
 *    create index idx_project_id on user(project_id);
 *    create index idx_user_id on user(user_id);
 *    create index idx_user_name on user(user_name);
 *    create index idx_project_id_user_id on user(project_id, user_id);
 * 3. 性能监控表
 *   create table performance(
 *   performance_id int primary key auto_increment,
 *   project_id int,
 *   user_id int,
 *   page_url varchar(255),
 *   sdk_version varchar(255),
 *   report_time timestamp,
 *   user_id int,
 *   uuid varchar(255),
 *   value int,
 *   device_info varchar(255),
 *   api_key varchar(255)
 *   );
 *   alter table performance add constraint fk_project_id foreign key(project_id) references project(project_id);
 *   alter table performance add constraint fk_user_id foreign key(user_id) references user(user_id);
 *
 */