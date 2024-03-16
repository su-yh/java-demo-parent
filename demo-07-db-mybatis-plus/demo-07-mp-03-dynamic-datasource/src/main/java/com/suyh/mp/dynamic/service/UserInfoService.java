package com.suyh.mp.dynamic.service;

import com.suyh.mp.dynamic.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author suyh
 * @since 2024-03-16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService {
    private final UserInfoMapper userInfoMapper;
}
