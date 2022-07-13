package com.ijeeva.libmgmtservice.dao;

import com.ijeeva.libmgmtservice.entities.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> groupByPriceCountBooks() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery
                .multiselect(
                        root.get("price"),
                        criteriaBuilder.count(root.get("price"))
                )
                .groupBy(root.get("price"));

        List<Object[]> results = em.createQuery(criteriaQuery)
                .getResultList();
        return results;
    }
}
