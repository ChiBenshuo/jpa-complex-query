package com.tashan.complexquery.logic;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 * 字段表达式
 * @author chi19
 */
public interface QueryExpress<T> {
    Expression<T> express(Root<?> root);
}
