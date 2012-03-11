package org.dao.util;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
 
public  class JPAUtil {

   static  int  etyvertion=0;
   static  int  etyOldvertion=0;
    private static  EntityManagerFactory entityManagerFactory;
    private static  EntityManager entityManager;

    static {
        try {
            //how to start derby database

            entityManagerFactory = Persistence.createEntityManagerFactory("InvoicingSystemPU");
            entityManager = entityManagerFactory.createEntityManager();

        } catch (Throwable e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
   
        return entityManagerFactory.createEntityManager();
    }


    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static Query createQuery(String key) {
        Query query = entityManager.createQuery(key);
        return query;
    }

    public static Connection getConnection() {
        Connection connection = entityManager.unwrap(Connection.class);
        return connection;
    }

    public static Connection getNewConnection() {
        Connection connection = entityManagerFactory.createEntityManager().unwrap(Connection.class);
        return connection;
    }



//    /**
//    @return returns a EntityManagerFactory for creating an EntityManager
//     */
    /**
     * Free up the resources consumed by the EntityManagerFactory.
     * Dont invoke this method at the middle of application.
     */
    public static void shutDownPersistenceFactory() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void startPersistence(){
     try {
            //how to start derby database

            entityManagerFactory = Persistence.createEntityManagerFactory("RaheemiyaPU");
            entityManager = entityManagerFactory.createEntityManager();

        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void clearCache() {
        entityManagerFactory.getCache().evictAll();
    }


    
}
