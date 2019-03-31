package com.tashan.complexquery.logic.constant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;
import java.util.List;

/**
 *
 * @author chi19
 */
public enum Compare {
    // 等于
    EQ() {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.equal(left, right.get(0));
        }
    },
    // 小于等于
    LE {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.lessThanOrEqualTo(left, right.get(0));
        }
    },
    // 小于
    LT {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.lessThan(left, right.get(0));
        }
    },
    // 大于
    GT {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.greaterThan(left, right.get(0));
        }
    },
    // 大于等于
    GE {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.greaterThanOrEqualTo(left, right.get(0));
        }
    },
    // 属于
    IN {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return left.in(right);
        }
    },
    // 为空
    ISNULL {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return left.isNull();
        }
    },
    // 不为空
    NOTNULL {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return left.isNotNull();
        }
    },
    // 存在
    EXIST {
        @Override
        public <T extends Comparable<T>> Predicate getPredicate(
                Expression<T> left, CriteriaBuilder builder, List<T> right,
                Subquery<?> subquery) {
            return builder.exists(subquery);
        }
    };

    public abstract <T extends Comparable<T>> Predicate getPredicate(
            Expression<T> left, CriteriaBuilder builder, List<T> right,
            Subquery<?> subquery);

}
