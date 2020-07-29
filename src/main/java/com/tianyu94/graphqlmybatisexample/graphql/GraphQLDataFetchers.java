package com.tianyu94.graphqlmybatisexample.graphql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tianyu94.graphqlmybatisexample.entity.User;
import com.tianyu94.graphqlmybatisexample.mapper.UserMapper;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLDataFetchers {
    private final UserMapper userMapper;

    public GraphQLDataFetchers(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public DataFetcher<User> getUserByIdDataFetcher() {
        return env -> {
            String id = env.getArgument("id");
            return userMapper.selectById(id);
        };
    }

    public DataFetcher<List<User>> getUsersByNameDataFetcher() {
        return env -> {
            String name = env.getArgument("name");
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(User::getName, name);
            return userMapper.selectList(wrapper);
        };
    }
}
