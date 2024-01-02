package com.suyh.constants;

/**
 * mybatis 中使用常量
 * 参考博客：https://www.cnblogs.com/sxdcgaq8080/p/9205495.html
 * <if test='vo.serviceType == null or vo.serviceType == "" '>
 * AND m.service_type != '${@com.suyh.constants.TempConstants@SERVICE_TYPE}'
 * </if>
 *
 * @since 2021-07-30
 */
public interface TempConstants {
  String SERVICE_TYPE = "value";
}
