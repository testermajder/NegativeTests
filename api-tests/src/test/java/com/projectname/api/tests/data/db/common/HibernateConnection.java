package com.projectname.api.tests.data.db.common;

import com.projectname.api.tests.data.db.model.Example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateConnection {

    public static void main(String[] args) {
        SessionFactory factory = Config.exampleDB().addAnnotatedClass(Example.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Example example = session.get(Example.class, 160);
            String user = example.getUser();

            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }

    public static void deleteUser(String userId){
        SessionFactory sessionFactory = Config.exampleDB().buildSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Example> criteriaQuery = criteriaBuilder.createQuery(Example.class);
            Root<Example> root = criteriaQuery.from(Example.class);
            criteriaQuery.where(
                    root.get("id").in(Arrays.asList(Integer.parseInt(userId)))
            );
            List<Example> toDelete = session.createQuery(criteriaQuery).getResultList();

            if (toDelete!= null) {
                for (int i = 0; i < toDelete.size(); i++) {
                    session.delete(toDelete.get(i));
                }
            }
            transaction.commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static Example getUserData(String userId, String userEmail){
        SessionFactory sessionFactory = Config.exampleDB().buildSessionFactory();
        Transaction transaction = null;
        List<Example> user = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Example> criteriaQuery = criteriaBuilder.createQuery(Example.class);
            Root<Example> root = criteriaQuery.from(Example.class);
            Predicate id = criteriaBuilder.equal(root.get("id"), userId);
            Predicate email = criteriaBuilder.equal(root.get("email"), userEmail);
            criteriaQuery.where(
                    root.get("employeeByAuthorId").in(Arrays.asList(Integer.parseInt(userId)))
            );
            criteriaQuery.select(root).where(criteriaBuilder.and(id, email));
            user  = session.createQuery(criteriaQuery).getResultList();

            transaction.commit();
        } finally {
            sessionFactory.close();
        }

        return user.get(0);
    }

}
