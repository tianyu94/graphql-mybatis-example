package com.tianyu94.graphqlmybatisexample.resource;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tianyu94.graphqlmybatisexample.entity.User;
import com.tianyu94.graphqlmybatisexample.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserMapper userMapper;

    public UserResource(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userMapper.selectList(Wrappers.emptyWrapper());
        return ResponseEntity.ok(users);
    }
}
