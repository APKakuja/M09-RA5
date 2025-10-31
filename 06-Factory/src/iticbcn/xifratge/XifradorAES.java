package iticbcn.xifratge;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


public class XifradorAES {
public static final String ALGORITME_XIFRAT = "AES";
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora ïlla Ôtto"
        };

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("------------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] dades = msg.getBytes("UTF-8");

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

        for (int i = 0; i < iv.length; i++) {
            resultat[i] = iv[i];
        }

        for (int i = 0; i < xifrat.length; i++) {
            resultat[iv.length + i] = xifrat[i];
        }

        return resultat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        for (int i = 0; i < MIDA_IV; i++) {
            iv[i] = bIvIMsgXifrat[i];
        }
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        int xifratLen = bIvIMsgXifrat.length - MIDA_IV;
        byte[] xifrat = new byte[xifratLen];
        for (int i = 0; i < xifratLen; i++) {
            xifrat[i] = bIvIMsgXifrat[MIDA_IV + i];
        }

        MessageDigest md = MessageDigest.getInstance(ALGORITME_HASH);
        byte[] keyBytes = md.digest(clau.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decBytes = cipher.doFinal(xifrat);

        return new String(decBytes, "UTF-8");
    }
}