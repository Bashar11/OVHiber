package Dao;

import Domein.OVChipkaart;
import Domein.Product;
import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;

import java.util.List;

public interface OVChipkaartDao {
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws DaoException;
    public boolean save(OVChipkaart ovkaart) throws DaoException;
    public boolean update(OVChipkaart ovkaart) throws DaoException;
    public boolean delete(OVChipkaart ovkaart) throws DaoException;
    public List<OVChipkaart>findAll();
    public OVChipkaart findByKaartNr(int nr) throws DaoException;
    public List<OVChipkaart> findByProduct(Product product);
}
