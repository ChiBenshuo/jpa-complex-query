package com.tashan.complexquery;

import com.tashan.complexquery.logic.QueryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 复杂查询
 * @param <T>
 */
public class ComplexQuery<T> {

    @Autowired
    private JpaSpecificationExecutor<T> repo;

    public Page<T> queryList(QueryLogic logic, Pageable pageable) {
        return repo.findAll(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                return logic.getPredicate(root, criteriaBuilder);
            }
        }, pageable);

    }
}
