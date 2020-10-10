
import Dao.ReizigerDao;
import Domein.Adres;
import Domein.OVChipkaart;
import Domein.Reiziger;
import HiberJDBC.AdresHibernate;
import HiberJDBC.OVChipkaartHibernate;
import HiberJDBC.ReizigerHibernate;
import HiberJDBC.daoException.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure("persistence.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException, DaoException {
        ReizigerHibernate rdao = new ReizigerHibernate(getSession());
        AdresHibernate adao = new AdresHibernate(getSession());
        OVChipkaartHibernate ovdao = new OVChipkaartHibernate(getSession());
//        testFetchAll();
//        testReizigerDAO(rdao);
          testAdresDao(adao,rdao);
        testOV(ovdao,rdao);
    }




    private static void testReizigerDAO(ReizigerHibernate rdao) throws SQLException, DaoException {
//        List<Reiziger>reizigers = rdao.findAll();
//        for (Reiziger reiziger: reizigers)
//            System.out.println(reiziger);

//        Reiziger reiziger = new Reiziger(7,"l","van","karl",Date.valueOf("1998-01-01"));
//        Reiziger reiziger1 = new Reiziger(19,"s","vaa","kal",Date.valueOf("1998-01-01"));
////        rdao.save(reiziger1);
//        //        rdao.save(reiziger);
//        rdao.delete(reiziger1);
//        reiziger.setNaam("Barr");
//        rdao.update(reiziger);

//        Reiziger r = rdao.findById(1);
//        System.out.println(r);
//
//        List<Reiziger>result = rdao.findByGbDatum("2002-12-03");
//        System.out.println(result);








//

    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */

//    private static void testFetchAll() {
//        Session session = getSession();
//        try {
//            Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                Query query = session.createQuery("from " + entityType.getName());
//
//                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//                System.out.println();
//            }
//        } finally {
//            session.close();
//        }
//    }
    private static void testAdresDao(AdresHibernate adao,ReizigerHibernate rdao) throws DaoException {
        Reiziger reiziger1 = new Reiziger(19,"s","vaa","kal",Date.valueOf("1998-01-01"));
//        rdao.save(reiziger1);
        Adres adres = new Adres(13,"1333AA","Bergstratt","12","Almere",reiziger1);
//        adao.save(adres);

//        adres.setStraat("Bergmolenstraat");
//        adao.update(adres);
//        adao.delete(adres);
        Adres adres1 = adao.findByReiziger(reiziger1);
        System.out.println(adres1);

//        Adres adres1 = adao.findById(13);
//        System.out.println(adres1);
//
//        List<Adres> adressen = adao.findAll();
//        for(Adres adres2 : adressen){
//            System.out.println(adres2);
//        }



    }
    private static void testOV(OVChipkaartHibernate ovdao, ReizigerHibernate rdao) throws DaoException {
//        OVChipkaart ov = ovdao.findByKaartNr(35283);
//        System.out.println(ov);
//        List<OVChipkaart> ovChipkaarts = ovdao.findByReiziger(rdao.findById(2));
//        for (OVChipkaart ov : ovChipkaarts){
//            System.out.println(ov);
//        }
    }
}