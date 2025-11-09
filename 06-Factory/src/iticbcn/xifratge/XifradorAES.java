package iticbcn.xifratge;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class XifradorAES implements Xifrador {
    public static final String ALGORITME_XIFRAT = "AES";
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final String CLAU_DEFECTE = "LaClauSecretaQueVulguis"; 

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] dades = msg.getBytes("UTF-8");

            byte[] iv = new byte[MIDA_IV];
            SecureRandom rnd = new SecureRandom();
            rnd.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            MessageDigest md = MessageDigest.getInstance(ALGORITME_HASH);
            byte[] keyBytes = md.digest(clau.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] xifrat = cipher.doFinal(dades);

            int totalLen = iv.length + xifrat.length;
            byte[] resultat = new byte[totalLen];
            System.arraycopy(iv, 0, resultat, 0, iv.length);
            System.arraycopy(xifrat, 0, resultat, iv.length, xifrat.length);

            return new TextXifrat(resultat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1); 
            return null; 
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bIvIMsgXifrat = xifrat.getBytes();

            byte[] iv = new byte[MIDA_IV];
            System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            int xifratLen = bIvIMsgXifrat.length - MIDA_IV;
            byte[] xifratBytes = new byte[xifratLen];
            System.arraycopy(bIvIMsgXifrat, MIDA_IV, xifratBytes, 0, xifratLen);

            MessageDigest md = MessageDigest.getInstance(ALGORITME_HASH);
            byte[] keyBytes = md.digest(clau.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decBytes = cipher.doFinal(xifratBytes);

            return new String(decBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}
