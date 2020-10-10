package HiberJDBC;

import Dao.ReizigerDao;
import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.TypedQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerHibernate implements ReizigerDao {
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }


    public ReizigerHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;


    }


    @Override
    public List findAll() throws DaoException{
        EntityTransaction transaction = null;
       List<Reiziger> result = new ArrayList<>();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Reiziger> query = entityManager.createQuery("SELECT e FROM Reiziger e",Reiziger.class);
            result = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DaoException(e);
    }
       return result ;


    }




    @Override
    public boolean save(Reiziger reiziger) throws DaoException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(reiziger);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) throws DaoException{
        EntityTransaction transaction = null;


        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(reiziger);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return false;
    }


    @Override
    public boolean delete(Reiziger reiziger) throws DaoException{
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(reiziger);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }

        return false;
    }




    @Override
    public Reiziger findById(int id) {
        EntityTransaction transaction = null;
        Reiziger result = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            result = entityManager.find(Reiziger.class, id);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum) throws DaoException{
        EntityTransaction transaction = null;
        List<Reiziger> result = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Reiziger> query = entityManager.createQuery("SELECT m FROM Reiziger m WHERE m.geboortedatum = :datum",Reiziger.class);
            result = query.setParameter("datum", Date.valueOf(datum)).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return result;
    }




}
