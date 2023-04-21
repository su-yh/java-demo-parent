package com.huawei.arms.common.tomcat;

import com.huawei.arms.base.exception.JalorArmsException;
import com.huawei.arms.common.slots.system.ArmsSystemErrorCountListener;
import com.huawei.arms.common.slots.system.ArmsSystemRuleElementManager;
import com.huawei.arms.common.slots.system.ArmsSystemStatusListener;
import io.micrometer.core.instrument.binder.tomcat.TomcatMetrics;
import lombok.extern.slf4j.Slf4j;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.util.Set;

@Slf4j
public class TomcatThreadMetricImpl implements TomcatThreadMetric {

    private final MBeanServer beanServer;

    public TomcatThreadMetricImpl() {
        beanServer = TomcatMetrics.getMBeanServer();
    }

    @Override
    public void init() {
        log.info("TomcatThreadMetricImpl init");
        ArmsSystemRuleElementManager.setTomcatThreadMetric(this);
        ArmsSystemStatusListener.setTomcatThreadMetric(this);
        ArmsSystemErrorCountListener.setTomcatThreadMetric(this);
    }

    @Override
    public int currentThreadsBusy() {
        try {
            Object currentThreadsBusy = getAttributeValue("currentThreadsBusy", ":type=ThreadPool,name=*");
            if (currentThreadsBusy == null) {
                return 0;
            }
            return Math.max(Integer.parseInt(currentThreadsBusy.toString()), 0);
        } catch (Exception exception) {
            log.error("tomcat threads busy exception.", exception);
        }
        return 0;
    }

    @Override
    public int currentThreadMax() {
        try {
            Object currentThreadsMax = this.getAttributeValue("maxThreads", ":type=ThreadPool,name=*");
            if (currentThreadsMax == null) {
                return 0;
            }
            return Integer.parseInt(currentThreadsMax.toString());
        } catch (Exception exc) {
            log.error("tomcat threads busy exception.", exc);
            return 0;
        }
    }

    @Override
    public int currentRequestTotalCount() {
        try {
            Object requestCountObj = this.getAttributeValue("requestCount", ":j2eeType=Servlet,name=*,*");
            return Integer.parseInt(requestCountObj.toString());
        } catch (AttributeNotFoundException | MBeanException | ReflectionException | InstanceNotFoundException exc) {
            log.error("error count is error :", exc.getMessage());
        }
        return 0;
    }


    @Override
    public int currentTomcatRequestTotalCount() {
        try {
            Object obj = getAttributeValue("requestCount", ":type=GlobalRequestProcessor,name=*");
            return Integer.parseInt(obj.toString());
        } catch (ReflectionException|InstanceNotFoundException| AttributeNotFoundException| MBeanException exc) {
            log.error("request total count info {}:",exc.getMessage());
        }
        return 0;
    }

    @Override
    public int currentErrorCount() {
        try {
            Object errorCountObj = this.getAttributeValue("errorCount", ":j2eeType=Servlet,name=*,*");
            return Integer.parseInt(errorCountObj.toString());
        } catch (AttributeNotFoundException | MBeanException | ReflectionException | InstanceNotFoundException exc) {
            log.error("error count is error :", exc.getMessage());
        }
        return 0;
    }

    /**
     * 参考：io.micrometer.core.instrument.binder.tomcat.TomcatMetrics#getNamePattern(String)
     *
     * @param namePatternSuffix 前缀
     * @return objectName
     */
    public static ObjectName getNamePattern(String namePatternSuffix) {
        try {
            return new ObjectName("Tomcat" + namePatternSuffix);
        } catch (MalformedObjectNameException exception) {
            // should never happen
            throw new JalorArmsException("Error registering Tomcat JMX based metrics", exception);
        }
    }

    private Object getAttributeValue(String attributName, String typeName) throws AttributeNotFoundException,
            MBeanException, ReflectionException, InstanceNotFoundException {
        Set<ObjectName> objectNames = beanServer.queryNames(getNamePattern(typeName), null);
        if (objectNames == null || objectNames.isEmpty()) {
            return null;
        }
        ObjectName name = objectNames.iterator().next();
        return beanServer.getAttribute(name, attributName);
    }
}
