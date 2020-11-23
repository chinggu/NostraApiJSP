/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.api.daoimp;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import web.api.conf.HibernateUtil;

import web.api.conf.HibernateUtil;
import web.api.dao.DaoMovie;
import web.api.model.Movies;

/**
 *
 * @author User
 */
/**
 *
 * @author Asp_net
 */
@Component
public class DaoImpMovie implements DaoMovie {

    @Override
    public List getAll() {
        List listData = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "FROM movies S";
            Query query = session.createQuery(hql);
            listData = query.list();
        } catch (Exception e) {
            listData.add("ERR " + e.getMessage());
        }
        session.flush();
        session.close();
        return listData;
    }

    @Override
    public Movies getMovie(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Movies movie = new Movies();
        try {
            movie = (Movies) session.get(Movies.class, id);
        } catch (Exception e) {
        }
        session.flush();
        session.close();
        return movie;
    }

    @Override
    public int insertMovie(Movies movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();
        int status;
        try {
            session.save(movie);
            trx.commit();
            status = 0;
        } catch (Exception e) {
            status = 1;
            trx.rollback();
        }
        session.clear();
        session.close();
        return status;
    }

    @Override
    public int updateMovie(Movies movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();
        int status;
        try {
            session.saveOrUpdate(movie);
            trx.commit();
            status = 0;
        } catch (Exception e) {
            status = 1;
            trx.rollback();
        }
        session.clear();
        session.close();
        return status;
    }

    @Override
    public int deleteMovie(int id) {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();
        int result;
        try {
            String hql = "delete from movies where id=" + id;
            Query query = session.createQuery(hql);
            query.executeUpdate();
            trx.commit();
            result = 0;
        } catch (Exception e) {
            result = 1;
            trx.rollback();
        }
        session.clear();
        session.close();
        return result;
    }
}
