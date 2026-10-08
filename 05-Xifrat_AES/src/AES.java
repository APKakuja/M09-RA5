
import java.security.SecureRandom;
import java.security.SecureRandomParameters;

public class AES {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "StringsForSpring";


    public static void main(String[] args) {
        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Agora ïlla Ôtto"
     };

     for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i];
        
        byte[] bXifrats = null;
        String desxifrat = "";
        try {
            bXifrats = xifraAES(msg, CLAU);
            desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
            System.out.println("Error de xifrat: " + e.getLocalizedMessage());
        }
        System.out.println("----------------------");
        System.out.println("Msg: " + msg);
        System.out.println("ENC: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
         
     }
    }

    public static byte[] xifraAES(String msg, String clau)
    throws Exception {

        SecureRandom randomSecureRandom = SecureRandom.getInstance("");
        byte[] iv = new byte[];

    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        return clau;
        
        // Extreu L'IV

        // Extreu la part xifrada

        // Fer hash de la clau

        // Desxifrar

        // return String desxifrat
    }
}
