package uo.sdi.persistence.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

public class Jpa {

	private static ThreadLocal<EntityManager> emThread = 
		new ThreadLocal<EntityManager>();
	
	public static EntityManager getManager() {
		EntityManager entityManager = emThread.get();
		if (entityManager == null) {
			entityManager = jndiFind("java:/ShareMyTripJpaEntityManager");
			emThread.set(entityManager);
		}
		return entityManager;
	}

	private static EntityManager jndiFind(String name) {
		Context ctx;
		try {
			ctx = new InitialContext();
			return (EntityManager) ctx.lookup(name);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
