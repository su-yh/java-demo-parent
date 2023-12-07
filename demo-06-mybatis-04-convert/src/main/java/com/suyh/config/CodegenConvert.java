//package com.suyh.config;
//
//import com.suyh.vo.CodegenColumnDO;
//import com.suyh.vo.CodegenTableDO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
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
//            @Mapping(source = "deleted", target = "deleted"),
//            @Mapping(source = "columnName", target = "column"),
//    })
//    CodegenTableDO convert(CodegenColumnDO bean);
//
//    List<CodegenColumnDO> convertList(List<CodegenTableDO> list);
//}
