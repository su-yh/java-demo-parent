package com.suyh2901.aware;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 这里给定的泛型 String 是@CreatedBy 的类型
 */
@Component
public class CustomerJpaAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // 一般这里都是从HTTP 请求的上下文里面获取到的
        String userBy = new String("suyh");
        if (StringUtils.isEmpty(userBy)) {
            return Optional.empty();
        }

        return Optional.of(userBy);
    }
}
