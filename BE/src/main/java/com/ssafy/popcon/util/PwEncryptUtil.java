package com.ssafy.popcon.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PwEncryptUtil {

    public static final int SALT_BYTES = 24;
    public static final int HASH_BYTES = 24;
    public static final int STRETCHING = 1000;
    static PwEncryptUtil instance = new PwEncryptUtil();

    private PwEncryptUtil() { }
    public static PwEncryptUtil getInstance() {
        return instance;
    }

    public static String makeSalt() {
        // 랜덤 salt 생성
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        return byte2string(salt);
    }

    public static String pwHashing(String userPw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] newPw = userPw.getBytes();
        for(int i = 0; i < STRETCHING; i++) {
            String temp = userPw + salt;
            md.update(temp.getBytes());
            newPw = md.digest();
        }

        return byte2string(newPw);
    }

    private static String byte2string(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

}