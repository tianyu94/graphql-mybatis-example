package com.tianyu94.graphqlmybatisexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tianyu94.graphqlmybatisexample.mapper")
@SpringBootApplication
public class GraphqlMybatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlMybatisExampleApplication.class, args);
    }

}
