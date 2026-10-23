package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    
    public KeyPair generaClausRSA() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(3000);
        KeyPair parellClaus = kpg.generateKeyPair();

        return parellClaus;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance(msg);
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        return cipher.doFinal(msg.getBytes());
        
    }

    public String desxifraRSA(byte[]msgXifrat, PrivateKey ClauPrivada) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);

        byte[] desen = cipher

        return null;

    }
}
