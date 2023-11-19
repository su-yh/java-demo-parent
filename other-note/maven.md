









```txt
MAVEN 介绍中文文档: http://ifeve.com/m2-getting-started/

maven command:
    mvn test
        编译并执行测试单元
    mvn compile
        编译源代码
    mvn test-compile
        仅编译不执行测试单元
    mvn package
    mvn install
    mvn site
    mvn clean
    mvn process-resources
        可以将pom.xml 中的属性数据，在"application.properties" 文件中被识别。如下
            # application.properties
            application.name=${project.name}
            application.version=${project.version}
        pom.xml 中的project.name 将被识别到 target/classes 路径下面的"application.properties"中。
        通过打包命令也可以做到。
    mvn process-resources "-Dcommand.line.prop=hello again"
        可以在mvn 时指定一些值。


问题记录：
    如果package 没有得到target 目录，则查检是不是只有 pom 而没有jar、war包的packaging 标签
    
    
```