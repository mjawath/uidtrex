package org.dao.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.config.PersistenceUnitProperties;

public class JPAUtil {

    static int etyvertion = 0;
    static int etyOldvertion = 0;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

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

    public static void startPersistence() {
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

    /**
     * reference http://wiki.eclipse.org/Using_EclipseLink_JPA_Extensions_%28ELUG%29#Using_EclipseLink_JPA_Extensions_for_Schema_Generation
     * key  persistence jpa ddl   Schema Generation
     *title  Using EclipseLink JPA Extensions for Schema Generation
     */
    public static void createEMFWithCustomProperties() {

        Map props = new HashMap();
//         props.put("eclipselink.jdbc.user","");
//         props.put("eclipselink.jdbc.password", "");\
        props.put(PersistenceUnitProperties.APP_LOCATION, "C:\\ddl\\");
        props.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
        props.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
        
        props.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "create.sql");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InvoicingSystemPU", props);
        entityManagerFactory = emf;
    }
}
