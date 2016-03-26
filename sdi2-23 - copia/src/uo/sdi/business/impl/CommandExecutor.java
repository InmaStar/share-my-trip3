package uo.sdi.business.impl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.persistence.util.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CommandExecutor {
    public static <T> T execute(Command<T> command) throws BusinessException {
        EntityManager em = Jpa.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();

        try {
            T res = command.execute();
            trx.commit();
            return res;
        } catch (RuntimeException e) {
            if (trx.isActive()) {
                trx.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
