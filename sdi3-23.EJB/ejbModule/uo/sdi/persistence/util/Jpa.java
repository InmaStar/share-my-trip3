package uo.sdi.persistence.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Jpa {
    private static EntityManagerFactory emf = null;
    private static ThreadLocal<EntityManager> emThread = new ThreadLocal<>();

    public static EntityManager createEntityManager() {
        EntityManager entityManager = getEmf().createEntityManager();
        emThread.set(entityManager);
        return entityManager;

    }

    public static EntityManager getManager() {
        if (emThread.get() == null) {
            createEntityManager();
        }
        return emThread.get();
    }

    public static EntityManagerFactory getEmf() {
        if (emf == null){
            emf = jndiFind("java:/ShareMyTripJpaFactory");
        }
        return emf;
    }

    private static EntityManagerFactory jndiFind(String name) {
        Context ctx;
        try {
            ctx = new InitialContext();

            return (EntityManagerFactory) ctx.lookup(name);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
