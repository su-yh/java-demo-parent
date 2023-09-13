package com.suyh2201.custom.validation.constraints;

import com.suyh2201.enums.NodeStatusEnums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NodeStatusEnumsValidator implements ConstraintValidator<NodeStatusEnumsMatch, NodeStatusEnums> {

    private NodeStatusEnums[] values;

    @Override
    public void initialize(NodeStatusEnumsMatch annotation) {
        values = annotation.value();
        if (this.values.length == 0) {
            this.values = new NodeStatusEnums[0];
        }
    }

    @Override
    public boolean isValid(NodeStatusEnums value, ConstraintValidatorContext context) {
        // 为空时，默认不校验，即认为通过
        if (value == null) {
            return true;
        }
        // 校验通过
        for (NodeStatusEnums nodeEnums : values) {
            if (nodeEnums.equals(value)) {
                return true;
            }
        }

        // TODO: suyh - 如果提示语不合适，还可以在这里修改
//        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
//        context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
//        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
//                .replaceAll("\\{value}", Arrays.toString(values))).addConstraintViolation(); // 重新添加错误提示语句

        return false;
    }

}

