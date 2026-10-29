import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    
     public int npass = 0;

    private static final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();

    private static final int MIN_LEN = 1;
    private static final int MAX_LEN = 6;

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            String hash;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest((salt + pw).getBytes());
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(bytes);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 no disponible", e);
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            String hash;
            int iterations = 20_000;
            int keyLenBits = 128; 
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLenBits);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] bytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(bytes);
            return hash;
        } catch (Exception e) {
            throw new RuntimeException("Error en PBKDF2", e);
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
    }


    public String getInterval(long t1, long t2) {
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!"; 

        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt),h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;

        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.println("================================");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("--- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.println("-------------------\n\n");
        }
    }
}
