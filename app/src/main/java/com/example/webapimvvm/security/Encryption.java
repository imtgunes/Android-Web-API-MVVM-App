package com.example.webapimvvm.security;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    private static final String SECRET_KEY = "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";

    private static final String SALT = "H7mgc8jIpXQg0/X6Cd/8yucYsHxFixQkiXHf/XD71O2EMwc0TQjK3StDUEp5NWbeL7qVwV43toJ43J3KL9D3D7VG+ZHIVz5OTXVLvEM1xVvfwPZGzWzwlham0mRRWKsPGH8109ULpzLBgQCaZ5ZypwXlNMsJtIxMSmG8Jwaooar/Y16ZPshg7ucq6mL7n5hv";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String strToEncrypt)
    {
        try {
            byte[] iv = { 2, 3, 9, 0, 7, 8, 2, 5,
                    9, 3, 5, 8, 4, 1, 0, 0 };
            IvParameterSpec ivspec
                    = new IvParameterSpec(iv);

            SecretKeyFactory factory
                    = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,
                    ivspec);

            return Base64.getEncoder().encodeToString(
                    cipher.doFinal(strToEncrypt.getBytes(
                            StandardCharsets.UTF_8)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String strToDecrypt)
    {
        try {

            byte[] iv = { 2, 3, 9, 0, 7, 8, 2, 5,
                    9, 3, 5, 8, 4, 1, 0, 0 };

            IvParameterSpec ivspec
                    = new IvParameterSpec(iv);


            SecretKeyFactory factory
                    = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");


            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,
                    ivspec);

            return new String(cipher.doFinal(
                    Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
