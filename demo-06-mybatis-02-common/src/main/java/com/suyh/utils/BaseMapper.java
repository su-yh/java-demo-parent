package com.suyh.utils;

import com.suyh.utils.impl.BaseMapperProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 这个类只用来做查看注释解释用。没有实际意义。
 *
 * @param <Model>  返回实体类
 * @param <Filter> 过滤器实体类
 * @RegisterMapper 这个注解与下面的配置必须要有一个。否则会报错。
 * mapper:
 * mappers: com.suyh.utils.BaseMapper
 */
//@RegisterMapper
public interface BaseMapper<Model, Filter> {

    // 查询使用SelectProvider，
    // 插入使用@InsertProvider，
    // 更新使用UpdateProvider，
    // 删除使用DeleteProvider。
    // 不同的Provider就相当于xml中不同的节点，
    // 如<select>,<insert>,<update>,<delete>。
    // 这4个Provider中的参数都一样，只有type和method

    /**
     * 参考博客：https://blog.csdn.net/ypp91zr/article/details/89006493
     * <p>
     * 查询使用SelectProvider，插入使用@InsertProvider，更新使用UpdateProvider，删除使用DeleteProvider。
     * 不同的Provider就相当于xml中不同的节点，如<select>,<insert>,<update>,<delete>。
     * 这4个Provider中的参数都一样，只有type和method。
     * <p>
     * type必须设置为实际执行方法的BaseMapperProvider.class(此类在下面),
     * 这个type 参数，实际就是自己实现的类
     * method必须设置为"dynamicSQL"。
     * 通用Mapper处理的时候会根据type反射BaseMapperProvider查找方法，
     * 而Mybatis的处理机制要求method必须是type类中只有一个入参，
     * 且返回值为String的方法。"dynamicSQL"方法定义在MapperTemplate中，该方法如下：
     * public String dynamicSQL(Object record) {
     * return "dynamicSQL";
     * }
     * <p>
     * 新建的BaseMapperProvider:上面第三点说到了需要一个类继承MapperTemplate，这是必须的。
     * 继承了它然后再进行相应的实现，方法名请和Mapper接口中的方法名一致
     *
     * @return
     */
//    @SelectProvider(type = BaseMapperProvider.class, method = "dynamicSQL")
//    List<Model> selectModelByFilter(@Param("filter") Filter filter);

    /**
     * 单表分页查询 - 博客给出的示例
     *
     * @param object
     * @param offset
     * @param limit
     * @return
     */
    @SelectProvider(type = BaseMapperProvider.class, method = "dynamicSQL")
    List<Model> selectPage(@Param("entity") Model object, @Param("offset") int offset,
                           @Param("limit") int limit);
}
