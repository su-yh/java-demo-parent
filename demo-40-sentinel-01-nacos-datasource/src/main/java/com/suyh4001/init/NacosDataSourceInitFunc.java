
package com.suyh4001.init;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Properties;

/**
 *
 * 以这种方式实现了之后，我们可以直接在nacos 的配置中心修改流控的规则，然后sentinel 客户端就会同步获取到最新的流控规则并立即更新生效。
 */
public class NacosDataSourceInitFunc implements InitFunc {
    /*
     * 配置中心json 格式配置示例：
     *
     * [
     *     {
     *         "resource": "TestResource",
     *         "limitApp": "default",
     *         "grade": 1,
     *         "count": 20,
     *         "strategy": 0,
     *         "controlBehavior": 0,
     *         "clusterMode": false
     *     }
     * ]
     */

    private static final String groupId = "DEFAULT_GROUP";
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

    /**
     * 这里仅仅实现了流控规则的动态规则监听
     * 如果还需要其它的则自行实现。
     */
    @Override
    public void init() throws Exception {
        // 在sentinel.properties 配置文件中添加自定义的配置属性
        String nacosServerAddress = SentinelConfig.getConfig("nacos.server-addr");
        String nacosNamespace = SentinelConfig.getConfig("nacos.namespace");
        Properties nacosProp = new Properties();
        nacosProp.setProperty(PropertyKeyConst.SERVER_ADDR, nacosServerAddress);
        if (StringUtils.isNotBlank(nacosNamespace)) {
            nacosProp.setProperty(PropertyKeyConst.NAMESPACE, nacosNamespace);
        }

        // suyh - T: List<FlowRule>
        // suyh - 这里就是将nacos 配置的数据拉取下来，并从一个String --> 转换成 List<FlowRule> 的对象。
        // suyh - 在nacos 的监听(NacosDataSource#configListener) 中将会动态获取并更新。
        // suyh - NacosDataSource 的构造方法中将创建这个configListener 的实例对象。
        Converter<String, List<FlowRule>> parser = source -> JSON.parseArray(source, FlowRule.class);
        // NacosDataSource 中有一个线程专门监听nacos 配置的变化，如果发生变更则立即更新 ReadableDataSource中的 SentinelProperty的值
        // 而SentinelProperty 中也有一个监听FlowRuleManager.LISTENER，它会同步更新FlowRuleManager 中的流控规则(FlowRuleManager.flowRules)。
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource
                = new NacosDataSource<>(nacosProp, groupId, dataId, parser);
        // 估计这里就是注册一个监听指定参数的规则
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
