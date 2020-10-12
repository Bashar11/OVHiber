package HiberJDBC;

import Dao.OVChipkaartDao;
import Domein.OVChipkaart;
import Domein.Product;
import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartHibernate implements OVChipkaartDao {
    private EntityManager entityManager;

    public OVChipkaartHibernate(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger)throws DaoException{
        EntityTransaction transaction = null;
        List<OVChipkaart> lijst = new ArrayList<>();
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<OVChipkaart> query = entityManager.createQuery("SELECT e FROM OVChipkaart e WHERE e.reiziger = :reiziger",OVChipkaart.class);
            lijst = query.setParameter("reiziger", reiziger).getResultList();
        }
        catch(Exception e ){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return lijst;
    }

    @Override
    public boolean save(OVChipkaart ovkaart) throws DaoException{
        EntityTransaction transaction= null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(ovkaart);
            transaction.commit();

        }catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovkaart) throws DaoException{
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(ovkaart);
            transaction.commit();

        }
        catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovkaart)throws DaoException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(ovkaart);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findAll() {
        EntityTransaction transaction = null;
        List<OVChipkaart> kaarten = new ArrayList<>();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<OVChipkaart> result = entityManager.createQuery("SELECT e FROM OVChipkaart e ",OVChipkaart.class);
            kaarten = result.getResultList();
            transaction.commit();

        }catch (Exception e ){
            if(transaction !=null && transaction.isActive()){}
            assert transaction != null;
            transaction.rollback();
        }

        return kaarten;
    }

    @Override
    public OVChipkaart findByKaartNr(int nr) throws DaoException{
        EntityTransaction transaction = null;
        OVChipkaart result = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            result = entityManager.find(OVChipkaart.class,nr);
            transaction.commit();

        }
        catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<OVChipkaart> findByProduct(Product product) throws DaoException {
        EntityTransaction transaction = null;
        List<OVChipkaart> result = new ArrayList<>();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<OVChipkaart> query = entityManager.createQuery("SELECT kaart FROM Product product JOIN product.kaarten kaart WHERE product.productNr = :product", OVChipkaart.class);
            result = query.setParameter("product",product.getProductNr()).getResultList();
            transaction.commit();
        }catch (Exception e){
            if(transaction !=null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return result;
    }
}
