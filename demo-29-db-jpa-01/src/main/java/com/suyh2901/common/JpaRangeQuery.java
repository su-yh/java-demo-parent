package com.suyh2901.common;

import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JpaRangeQuery<T> implements Specification<T> {
    private Example<T> example;
    private final List<IAttributeRange<?>> rangeList = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<String> ignorePaths = new ArrayList<>();
        for (IAttributeRange<?> rangeElement : rangeList) {
            if (rangeElement.isValid()) {
                ignorePaths.add(rangeElement.getAttribute());
            }
        }

        example.getMatcher().withIgnorePaths(ignorePaths.toArray(new String[0]));
        Predicate predicateExample = QueryByExamplePredicateBuilder.getPredicate(root, cb, example);

        List<Predicate> rangePredicate = new ArrayList<>();
        for (IAttributeRange<?> rangElement : rangeList) {
            if (rangElement.getLowerBound() != null) {
                if (rangElement.isContainLower()) {
                    rangePredicate.add(cb.greaterThanOrEqualTo(
                            root.get(rangElement.getAttribute()), rangElement.getLowerBound()));
                } else {
                    rangePredicate.add(cb.greaterThan(
                            root.get(rangElement.getAttribute()), rangElement.getLowerBound()));
                }
            }
            if (rangElement.getUpperBound() != null) {
                if (rangElement.isContainUpper()) {
                    rangePredicate.add(cb.lessThanOrEqualTo(
                            root.get(rangElement.getAttribute()), rangElement.getLowerBound()));
                } else {
                    rangePredicate.add(cb.lessThan(
                            root.get(rangElement.getAttribute()), rangElement.getLowerBound()));
                }
            }
        }

        if (rangePredicate.isEmpty()) {
            return predicateExample;
        }

        return cb.and(predicateExample, rangePredicate.toArray(new Predicate[0]));
    }

    public interface IAttributeRange<T> {
        String getAttribute();

        T getLowerBound();

        T getUpperBound();

        boolean isContainLower();

        boolean isContainUpper();

        boolean isValid();
    }

    @Data
    public static class AttributeRange<T> implements IAttributeRange<T>, Serializable {
        static final long serialVersionUID = 42L;

        private String attribute;   // 属性名称字符串
        private T lowerBound;   // 下边界
        private boolean containLower;   // 是否包含下边界
        private T upperBound;   // 上边界
        private boolean containUpper; // 是否包含上边界

        @Override
        public boolean isValid() {
            return lowerBound != null || upperBound != null;
        }
    }
}
