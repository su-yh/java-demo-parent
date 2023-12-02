//package com.suyh.config;
//
//import com.baomidou.mybatisplus.generator.config.po.TableField;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.suyh.vo.CodegenColumnDO;
//import com.suyh.vo.CodegenTableDO;
//import org.apache.ibatis.type.JdbcType;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
///**
// * 来自芋道的源代码示例
// */
//@Mapper
//public interface CodegenConvert {
//
//    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);
//
//    @Mappings({
//            @Mapping(source = "name", target = "tableName"),
//            @Mapping(source = "comment", target = "tableComment"),
//    })
//    CodegenTableDO convert(TableInfo bean);
//
//    List<CodegenColumnDO> convertList(List<TableField> list);
//
//    @Mappings({
//            @Mapping(source = "name", target = "columnName"),
//            @Mapping(source = "metaInfo.jdbcType", target = "dataType", qualifiedByName = "getDataType"),
//            @Mapping(source = "comment", target = "columnComment"),
//            @Mapping(source = "metaInfo.nullable", target = "nullable"),
//            @Mapping(source = "keyFlag", target = "primaryKey"),
//            @Mapping(source = "keyIdentityFlag", target = "autoIncrement"),
//            @Mapping(source = "columnType.type", target = "javaType"),
//            @Mapping(source = "propertyName", target = "javaField"),
//    })
//    CodegenColumnDO convert(TableField bean);
//
//    @Named("getDataType")
//    default String getDataType(JdbcType jdbcType) {
//        return jdbcType.name();
//    }
//}
