package Dao;

import Domein.OVChipkaart;
import Domein.Product;
import HiberJDBC.daoException.DaoException;

import java.util.List;

public interface ProductDao {
    public boolean save(Product product) throws DaoException;
    public boolean update(Product product) throws DaoException;
    public boolean delete(Product product) throws DaoException;
    public Product findByNr(int product) throws DaoException;
    public List<Product> findByOVChipkaart(OVChipkaart kaart) throws DaoException;
    public List<Product> findAll() throws DaoException;
}
