package com.tianyu94.graphqlmybatisexample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianyu94.graphqlmybatisexample.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
