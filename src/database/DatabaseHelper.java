package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Kyra on 08/04/2016.
 */
public class DatabaseHelper {



    public void addUser(String userName, String password) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36];
        random.nextBytes(bytes);
        printBytes(bytes);
        for (byte b: bytes) {
            password += b;
        }
        System.out.println(password);
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        printBytes(hash);
    }

    public void checkUserPassword(String userName, String password) {

    }

    public void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(b);
        }
        System.out.println(": " + bytes.length);
    }

    public static void main(String[] args) {
        DatabaseHelper obj = new DatabaseHelper();
        obj.addUser("schaap","blaat");
    }
}
