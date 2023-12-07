package com.suyh.convert;

import com.suyh.vo.CodegenColumnDO;
import com.suyh.vo.CodegenTableDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * 来自芋道的源代码示例
 */
@Mapper
public interface CodegenConvert {

    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);

    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "columnName", target = "column")
    CodegenTableDO convert(CodegenColumnDO bean);

    CodegenColumnDO convert(CodegenTableDO bean);

    default List<CodegenColumnDO> convertList(List<CodegenTableDO> list) {
        if (list == null) {
            return null;
        }

        List<CodegenColumnDO> listResult = new ArrayList<>();
        for (CodegenTableDO codegenTableDO : list) {
            CodegenColumnDO resultDo = convert(codegenTableDO);
            listResult.add(resultDo);
        }

        return listResult;
    }
}
