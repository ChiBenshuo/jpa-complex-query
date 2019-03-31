package com.tashan.complexquery.logic;

import com.tashan.complexquery.logic.constant.Compare;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author chi19
 * @param <T>
 */
public class QueryCondition<T extends Comparable<T>> extends QueryLogic {
    /** 比较符 */
    private Compare compare;
    /** 左 */
    private QueryExpress<T> left;
    /** 右 */
    private List<T> right = new ArrayList<>();
    private Subquery<?> subquery;

    private QueryCondition(QueryExpress<T> left, Compare compare,
            Collection<T> right) {
        this.compare = compare;
        this.left = left;
        this.right.addAll(right);
    }

    public static <T extends Comparable<T>> QueryCondition of(
            QueryExpress<?> left, Compare compare, Collection<T> right) {
        return new QueryCondition(left, compare, right);
    }

    public static <T extends Comparable<T>> QueryCondition of(
            QueryExpress<T> left, Compare compare, T right) {
        return new QueryCondition(left, compare, Arrays.asList(right));
    }

    @Override
    public Predicate getPredicate(Root<?> root, CriteriaBuilder builder) {
        return compare
                .getPredicate(left.express(root), builder, right, subquery);
    }
}
