package Dao;

import Domein.Reiziger;
import HiberJDBC.daoException.DaoException;

import java.util.List;

public interface ReizigerDao {
    public boolean save(Reiziger reiziger) throws DaoException;
    public boolean update(Reiziger reiziger) throws DaoException;
    public boolean delete(Reiziger reiziger) throws DaoException;
    public Reiziger findById(int id);
    public List<Reiziger> findByGbDatum(String datum) throws DaoException;
    public List<Reiziger> findAll() throws DaoException;
}
