package com.kmorarash.hwHibernate.repository.impl;

import com.kmorarash.hwHibernate.model.Role;
import com.kmorarash.hwHibernate.repository.RoleRepository;
import com.kmorarash.hwHibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Role create(Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public Optional<Role> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Role.class, id));
        }
    }
}
