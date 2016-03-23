package uo.sdi.persistence;

import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class UserFinder {

    public static User findById(Long id) {
        return Jpa.getManager()
                .find(User.class, id);
    }

    public static User findByLoginAndPassword(String login, String password) {
        return Jpa.getManager()
                .createNamedQuery("User.findByLoginAndPassword", User.class)
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
    }

    public static User findByLogin(String login) {
        return Jpa.getManager()
                .createNamedQuery("User.findByLogin", User.class)
                .setParameter(1, login)
                .getSingleResult();
    }
}
