package com.suyh4601.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyh4601.entity.master.MasterTestUser;
import com.suyh4601.entity.slave.SlaveTestUser;
import com.suyh4601.mapper.slave.SlaveTestUserMapper;
import com.suyh4601.vo.ResponseResult;
import com.suyh4601.mapper.master.MasterTestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class TestUserController {

    @Autowired
    private MasterTestUserMapper masterTestUserMapper;
    @Autowired
    private SlaveTestUserMapper slaveTestUserMapper;

    /**
     * 查询
     */
    @GetMapping("/find")
    public Object find(int id) {
        SlaveTestUser testUser = slaveTestUserMapper.selectOne(new QueryWrapper<SlaveTestUser>().eq("id" , id));
        if (testUser != null) {
            return ResponseResult.success(testUser);
        } else {
            return ResponseResult.error("没有找到该对象");
        }
    }

    /**
     * 查询全部
     */
    @GetMapping("/listall")
    public Object listAll() {
        //自定义接口查询
        QueryWrapper<MasterTestUser> queryWrapper = new QueryWrapper<>();
        List<MasterTestUser> resultData = masterTestUserMapper.selectAll(queryWrapper.isNotNull("name"));
        //mp内置接口
        List<SlaveTestUser> resultDataSlave = slaveTestUserMapper.selectList(null);
        int initSize = 30;
        Map<String, Object> result = new HashMap<>(initSize);
        result.put("master" , resultData);
        result.put("slave" , resultDataSlave);

        return ResponseResult.success(result);
    }

}
