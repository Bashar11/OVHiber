package Dao;

import Domein.Adres;
import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;

import java.util.List;

public interface AdresDao {
    public boolean save(Adres adres) throws DaoException;
    public boolean update(Adres adres)throws DaoException;
    public boolean delete(Adres adres)throws DaoException;
    public Adres findByReiziger(Reiziger reiziger) throws DaoException;
    public Adres findById(int id);
    public List<Adres> findAll();
}
