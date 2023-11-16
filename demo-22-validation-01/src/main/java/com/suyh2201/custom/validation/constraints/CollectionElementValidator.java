package com.suyh2201.custom.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class CollectionElementValidator implements ConstraintValidator<CollectionElement, Collection> {
    private boolean enableNull = false;

    @Override
    public void initialize(CollectionElement annotation) {
        enableNull = annotation.enableNull();
    }

    @Override
    public boolean isValid(Collection values, ConstraintValidatorContext context) {
        if (enableNull) {
            return true;
        }

        if (values != null) {
            for (Object value : values) {
                if (value == null) {
                    return false;
                }
            }
        }

        return true;
    }
}
