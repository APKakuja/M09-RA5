import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    // Charset que incluye minúsculas, mayúsculas, números y símbolo !
    private static final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();

    // Longitud fija de 6 (el objetivo es aaabF!)
    private static final int MIN_LEN = 6;
    private static final int MAX_LEN = 6;

    // ---------- Hashing ----------

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest((salt + pw).getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 no disponible", e);
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iterations = 20_000;
            int keyLenBits = 128; // 16 bytes => 32 hex
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLenBits);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] bytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error en PBKDF2", e);
        }
    }

    public String forcaBruta(String alg, String hashObjectiu, String salt) {
        npass = 0; // reinicia el contador por cada algoritmo

       
        for (int len = MIN_LEN; len <= MAX_LEN; len++) {
            char[] candidate = new char[len];
            if (cercaRecursiva(alg, hashObjectiu, salt, candidate, 0)) {
                return new String(candidate);
            }
        }
        return null;
    }

    private boolean cercaRecursiva(String alg, String hashObjectiu, String salt, char[] candidate, int pos) {
        if (pos == candidate.length) {
            String pw = new String(candidate);
            npass++;

            String hashGenerat = switch (alg) {
                case "SHA-512" -> getSHA512AmbSalt(pw, salt);
                case "PBKDF2" -> getPBKDF2AmbSalt(pw, salt);
                default -> throw new IllegalArgumentException("Algorisme no suportat: " + alg);
            };

            return hashGenerat.equals(hashObjectiu);
        }

        for (char c : CHARSET) {
            candidate[pos] = c;
            if (cercaRecursiva(alg, hashObjectiu, salt, candidate, pos + 1)) {
                return true;
            }
        }
        return false;
    }

    // ---------- Format de temps ----------

    public String getInterval(long t1, long t2) {
        long ms = Math.max(0, t2 - t1);
        long dies = ms / (24L * 60 * 60 * 1000); ms %= (24L * 60 * 60 * 1000);
        long hores = ms / (60L * 60 * 1000); ms %= (60L * 60 * 1000);
        long minuts = ms / (60L * 1000); ms %= (60L * 1000);
        long segons = ms / 1000; ms %= 1000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                dies, hores, minuts, segons, ms);
    }

    // ---------- Main ----------

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiurafslkdfjz";
        String pw = "aaabF!"; 

        Hashes h = new Hashes();

        String[] alashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String[] algoritmes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < alashes.length; i++) {
            System.out.print("==================================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", alashes[i]);
            System.out.print("\n");
            System.out.printf("--- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            String pwTrobat = h.forcaBruta(algoritmes[i], alashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.print("==================================\n\n");
        }
    }
}
