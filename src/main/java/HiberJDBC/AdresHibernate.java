package HiberJDBC;

import Dao.AdresDao;
import Domein.Adres;
import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdresHibernate implements AdresDao {
    private EntityManager entityManager;

    public AdresHibernate(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Adres adres) throws DaoException {

        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(adres);
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
    public boolean update(Adres adres)throws DaoException{
        EntityTransaction transaction = null;


        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(adres);
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
    public boolean delete(Adres adres) throws DaoException{
        EntityTransaction transaction = null;


        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(adres);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
            return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws DaoException {
        EntityTransaction transaction = null;
        Adres result = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Adres> query= entityManager.createQuery("SELECT m FROM Adres m WHERE m.reiziger = :reiziger",Adres.class);
            result = query.setParameter("reiziger",reiziger).getSingleResult();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Adres findById(int id) {
        EntityTransaction transaction = null;
        Adres result = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            result = entityManager.find(Adres.class, id);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public List<Adres> findAll() {
        EntityTransaction transaction = null;
        List<Adres> result = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            result = entityManager.createQuery("SELECT m FROM Adres m", Adres.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return result;

    }
}
