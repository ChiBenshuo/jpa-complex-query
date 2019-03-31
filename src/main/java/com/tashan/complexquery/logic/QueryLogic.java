package com.tashan.complexquery.logic;

import com.tashan.complexquery.logic.constant.Logic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author chi19
 */
public class QueryLogic {

    private QueryLogic left;
    private Logic logic;
    private QueryLogic right;

    public static QueryLogic of() {
        return new QueryLogic();
    }

    public QueryLogic and(QueryLogic right) {
        if (this.logic == null) {
            return right;
        }
        return new QueryLogic(this, Logic.AND, right);
    }

    public QueryLogic or(QueryLogic right) {
        if (this.logic == null) {
            return right;
        }
        return new QueryLogic(this, Logic.OR, right);
    }

    public QueryLogic not() {
        return new QueryLogic(this, Logic.NOT, null);
    }

    /**
     * 获取判定
     * @param root
     * @param builder
     * @return
     */
    public Predicate getPredicate(Root<?> root, CriteriaBuilder builder) {
        return logic.getPredicate(root, builder, left, right);
    }

    private QueryLogic(QueryLogic left, Logic logic, QueryLogic right) {
        this.left = left;
        this.logic = logic;
        this.right = right;
    }

    private QueryLogic() {
    }

}
