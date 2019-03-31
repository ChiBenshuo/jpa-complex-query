package com.tashan.complexquery.logic.constant;

import com.tashan.complexquery.logic.QueryLogic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author chi19
 */
public enum Logic {
    AND {
        @Override
        public Predicate getPredicate(Root<?> root, CriteriaBuilder builder,
                QueryLogic left, QueryLogic right) {
            return builder.and(left.getPredicate(root, builder),
                    right.getPredicate(root, builder));
        }
    },
    OR {
        @Override
        public Predicate getPredicate(Root<?> root, CriteriaBuilder builder,
                QueryLogic left, QueryLogic right) {
            return builder.or(left.getPredicate(root, builder),
                    right.getPredicate(root, builder));
        }
    },
    NOT {
        @Override
        public Predicate getPredicate(Root<?> root, CriteriaBuilder builder,
                QueryLogic left, QueryLogic right) {
            return left.getPredicate(root, builder).not();
        }
    };

    public abstract Predicate getPredicate(Root<?> root,
            CriteriaBuilder builder, QueryLogic left, QueryLogic right);}
