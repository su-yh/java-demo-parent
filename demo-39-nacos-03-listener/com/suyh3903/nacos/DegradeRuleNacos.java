
package com.suyh3903.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class DegradeRuleNacos extends AbstractNacosDynamic<List<DegradeRuleEntity>> {
    @Override
    protected AbstractNacosConfigListener newInstanceNacosConfig(String appName) throws NacosException {
        return new NacosConfigListenerListener(appName);
    }

    /**
     * 内部类：每一个应用一个相应nacos 配置监听类对象
     */
    private class NacosConfigListenerListener extends AbstractNacosConfigListener {
        private List<DegradeRuleEntity> configValue = new ArrayList<>();

        public NacosConfigListenerListener(String appName) throws NacosException {
            super("dataId", appName);
        }

        @Override
        protected void updateConfigValue(String value) {
            List<DegradeRuleEntity> tempValue = JsonUtil.deserializeToList(value, DegradeRuleEntity.class);
            configValue = tempValue == null ? new ArrayList<>() : tempValue;
        }

        @Override
        public List<DegradeRuleEntity> getConfigObject() {
            return configValue;
        }

        @Override
        protected String emptyConfigValue() {
            return emptyJsonList();
        }
    }

    public static String emptyJsonObject() {
        return "{}";
    }

    public static String emptyJsonList() {
        return "[]";
    }

}
