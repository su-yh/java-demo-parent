# java-demo-springboot

> if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getHeader("Accept"))) {
>                    response.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
>                }


重定向，转发
https://www.cnblogs.com/xiaoqi/p/spring-boot-route.html

# 初始化spring 项目
https://start.spring.io/


# spring boot 与spring cloud 的版本匹配

官网也有写版本映射：https://spring.io/projects/spring-cloud

链接：https://start.spring.io/actuator/info

```json
{
    "git": {
        "branch": "0d06d65bc91acfb7f828c8e2a579f018c0610056",
        "commit": {
            "id": "0d06d65",
            "time": "2021-10-07T16:05:14Z"
        }
    },
    "build": {
        "version": "0.0.1-SNAPSHOT",
        "artifact": "start-site",
        "versions": {
            "spring-boot": "2.5.5",
            "initializr": "0.11.1-SNAPSHOT"
        },
        "name": "start.spring.io website",
        "time": "2021-10-07T16:05:44.262Z",
        "group": "io.spring.start"
    },
    "bom-ranges": {
        "azure": {
            "3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
            "3.5.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1",
            "3.6.1": "Spring Boot >=2.5.0.M1 and <2.6.0-M1"
        },
        "codecentric-spring-boot-admin": {
            "2.4.3": "Spring Boot >=2.3.0.M1 and <2.6.0-M1"
        },
        "solace-spring-boot": {
            "1.1.0": "Spring Boot >=2.3.0.M1 and <2.6.0-M1"
        },
        "solace-spring-cloud": {
            "1.1.1": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
            "2.1.0": "Spring Boot >=2.4.0.M1 and <2.6.0-M1"
        },
        "spring-cloud": {
            "Hoxton.SR12": "Spring Boot >=2.2.0.RELEASE and <2.4.0.M1",
            "2020.0.4": "Spring Boot >=2.4.0.M1 and <2.5.6-SNAPSHOT",
            "2020.0.5-SNAPSHOT": "Spring Boot >=2.5.6-SNAPSHOT and <2.6.0-M1",
            "2021.0.0-M1": "Spring Boot >=2.6.0-M1 and <2.6.0-M3",
            "2021.0.0-M2": "Spring Boot >=2.6.0-M3 and <2.6.0-SNAPSHOT",
            "2021.0.0-SNAPSHOT": "Spring Boot >=2.6.0-SNAPSHOT"
        },
        "spring-cloud-gcp": {
            "2.0.4": "Spring Boot >=2.4.0-M1 and <2.6.0-M1"
        },
        "spring-cloud-services": {
            "2.3.0.RELEASE": "Spring Boot >=2.3.0.RELEASE and <2.4.0-M1",
            "2.4.1": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
        },
        "spring-geode": {
            "1.3.12.RELEASE": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
            "1.4.11": "Spring Boot >=2.4.0-M1 and <2.5.0-M1",
            "1.5.5": "Spring Boot >=2.5.0-M1 and <2.6.0-M1",
            "1.6.0-M3": "Spring Boot >=2.6.0-M1"
        },
        "vaadin": {
            "14.7.1": "Spring Boot >=2.1.0.RELEASE and <2.6.0-M1"
        },
        "wavefront": {
            "2.0.2": "Spring Boot >=2.1.0.RELEASE and <2.4.0-M1",
            "2.1.1": "Spring Boot >=2.4.0-M1 and <2.5.0-M1",
            "2.2.0": "Spring Boot >=2.5.0-M1"
        }
    },
    "dependency-ranges": {
        "native": {
            "0.9.0": "Spring Boot >=2.4.3 and <2.4.4",
            "0.9.1": "Spring Boot >=2.4.4 and <2.4.5",
            "0.9.2": "Spring Boot >=2.4.5 and <2.5.0-M1",
            "0.10.0": "Spring Boot >=2.5.0-M1 and <2.5.2",
            "0.10.1": "Spring Boot >=2.5.2 and <2.5.3",
            "0.10.2": "Spring Boot >=2.5.3 and <2.5.4",
            "0.10.3": "Spring Boot >=2.5.4 and <2.5.5",
            "0.10.4": "Spring Boot >=2.5.5 and <2.5.6-SNAPSHOT",
            "0.10.5-SNAPSHOT": "Spring Boot >=2.5.6-SNAPSHOT and <2.6.0-M1",
            "0.11.0-M1": "Spring Boot >=2.6.0-M1 and <2.6.0-SNAPSHOT",
            "0.11.0-SNAPSHOT": "Spring Boot >=2.6.0-SNAPSHOT and <2.7.0-M1"
        },
        "okta": {
            "1.4.0": "Spring Boot >=2.2.0.RELEASE and <2.4.0-M1",
            "1.5.1": "Spring Boot >=2.4.0-M1 and <2.4.1",
            "2.0.1": "Spring Boot >=2.4.1 and <2.5.0-M1",
            "2.1.2": "Spring Boot >=2.5.0-M1 and <2.6.0-M1"
        },
        "mybatis": {
            "2.1.4": "Spring Boot >=2.1.0.RELEASE and <2.5.0-M1",
            "2.2.0": "Spring Boot >=2.5.0-M1"
        },
        "camel": {
            "3.5.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
            "3.10.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1",
            "3.12.0": "Spring Boot >=2.5.0.M1 and <2.6.0-M1"
        },
        "open-service-broker": {
            "3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
            "3.3.0": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
        }
    }
}
```

