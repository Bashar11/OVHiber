package HiberJDBC;

import Dao.ProductDao;
import Domein.OVChipkaart;
import Domein.Product;
import HiberJDBC.daoException.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductHibernate implements ProductDao {
    private EntityManager entityManager;

    public ProductHibernate(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public boolean save(Product product) throws DaoException {
        EntityTransaction transaction = null ;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();

        }catch(Exception e){
            if(transaction !=null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean update(Product product) throws DaoException {
        EntityTransaction transaction = null ;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(product);
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
    public boolean delete(Product product) throws DaoException {
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(product);
            transaction.commit();
        }
        catch (Exception e ){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public Product findByNr(int product) throws DaoException {
        EntityTransaction transaction = null;
        Product result;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            result = entityManager.find(Product.class,product);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart kaart) {
        EntityTransaction transaction = null;
        List<Product> result = new ArrayList<>();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.kaart = :kaart", Product.class);
            result = query.setParameter("kaart",kaart).getResultList();
            transaction.commit();
        }catch (Exception e){
            if(transaction !=null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public List<Product> findAll() throws DaoException {
        EntityTransaction transaction = null;
        List<Product> lijst;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Product> query = entityManager.createQuery("SELECT r FROM Product r", Product.class);
            lijst = query.getResultList();
            transaction.commit();

        }
        catch (Exception e ){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new DaoException(e);
        }
        return lijst;    }
}
