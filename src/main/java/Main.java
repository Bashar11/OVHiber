
import Domein.Adres;
import Domein.OVChipkaart;
import Domein.Product;
import Domein.Reiziger;
import HiberJDBC.AdresHibernate;
import HiberJDBC.OVChipkaartHibernate;
import HiberJDBC.ProductHibernate;
import HiberJDBC.ReizigerHibernate;
import HiberJDBC.daoException.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        ProductHibernate pdao = new ProductHibernate(getSession());
//        testFetchAll();
//        testReizigerDAO(rdao);
        testAdresDao(adao,rdao);
        testOV(ovdao,rdao);
        tesProd(pdao,ovdao,rdao);
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
//        Adres adres1 = adao.findByReiziger(reiziger1);
//        System.out.println(adres1);

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
        Reiziger reiziger = new Reiziger(20,"b","aw","kado",Date.valueOf("1990-01-01"));
//        rdao.save(reiziger);

        OVChipkaart ovChipkaart = new OVChipkaart(43232,Date.valueOf("2023-01-01"),2,30.0,reiziger);
//        ovdao.save(ovChipkaart);

//        ovChipkaart.setSaldo(30.31);
//        ovdao.update(ovChipkaart);
//
//        OVChipkaart o = ovdao.findByKaartNr(43232);
//        System.out.println(o);

//        List<OVChipkaart> ovd = ovdao.findByReiziger(reiziger);
//        for (OVChipkaart ovChipkaart1 : ovd){
//            System.out.println(ovChipkaart1);
//        }
//        ovdao.delete(ovChipkaart);
    }
    private static void tesProd(ProductHibernate pdao, OVChipkaartHibernate ovdao, ReizigerHibernate rdao) throws DaoException {
        Reiziger reiziger = new Reiziger(20,"b","aw","kado",Date.valueOf("1990-01-01"));
//        rdao.save(reiziger);
        OVChipkaart ovChipkaart1 = new OVChipkaart(43231,Date.valueOf("2023-01-01"),1,30.0,reiziger);
//        ovdao.save(ovChipkaart1);

        Product product =  new Product(7,"productTest","Dir is een product test",53.0);
//        pdao.save(product);
        product.setBeschrijving("Dit is product na update ");
//        pdao.update(product);

//        List<Product> res = pdao.findAll();
//        for(Product p : res){
//            System.out.println(p);
//        }
//
//        product.addKaart(ovChipkaart1);
//        ovChipkaart1.addProduct(product);
//        pdao.update(product);
//        ovdao.update(ovChipkaart1);
//
//        Product p = pdao.findByNr(7);
//        System.out.println(p);
//        List<OVChipkaart> pr = ovdao.findByProduct(product);
//        for(OVChipkaart p : pr){
//            System.out.println(p);
//        }
//
//        List<Product> ovf = pdao.findByOVChipkaart(ovChipkaart1);
//        for(Product o : ovf){
//            System.out.println(o);
//        }
//
//        pdao.delete(product);



    }
}