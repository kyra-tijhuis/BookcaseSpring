package database.dao;

import database.model.Bookcase;
import database.model.User;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Kyra on 14/04/2016.
 */
public class UserDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public User getUser(String userName) {
        User user = null;
        Query query = emf.createEntityManager().createQuery("from User u where userName= :username");
        query.setParameter("username", userName);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            // user stays null
        }
        return user;
    }

    /**
     * Create a user if the username does not exist, otherwise return null.
     * @param userName the user's username
     * @param password the user's password
     * @return the newly created user, or null if the user already existed.
     */
    public User createUser(String userName, String password) {
        User existingUser = getUser(userName);

        if (existingUser==null) {
            User user = new User();
            user.setUserName(userName);
            String salt = generateSalt();
            user.setSalt(salt);
            user.setPasswordHash(hashPasswordSalt(salt, password));

            EntityManager em = emf.createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(user);
            t.commit();
            em.close();
            return user;
        } else {
            return null;
        }
    }

    /**
     * Update the User parameter before calling this method!
     * @param user the updated User object
     * @return updated user
     */
    public User updateUser(User user) {
        User result = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        result = em.merge(user);

        t.commit();
        em.close();
        return result;
    }

    private String generateSalt() {
        String salt = "";
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36];
        random.nextBytes(bytes);
        for (byte b: bytes) {
            salt += b;
        }
        return salt;
    }

    private String hashPasswordSalt(String salt, String password) {
        password += salt;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        String password_hash = "";
        for (byte b : hash) {
            password_hash += b;
        }
        return password_hash;
    }

    private String hashPassword(String userName, String password){
        return hashPasswordSalt(getUser(userName).getSalt(), password);
    }

    private String getHash(String userName) {
        return getUser(userName).getPasswordHash();
    }

    public boolean correctPassword(String userName, String password) {
        return hashPassword(userName, password).equals(getHash(userName));
    }

}
