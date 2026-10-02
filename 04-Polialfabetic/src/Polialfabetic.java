import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    static char[] alfabet = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private static Long clauSecreta;
    static Random rand = new SecureRandom();

    public static String getLlavor(long lenght) {
        assert lenght >= 4;
        char[] llavor = new char[(int) lenght];

        for (int i=4; i<lenght; i++) {
            llavor[i] = alfabet[rand.nextInt(alfabet.length)];
        }

        return new String(llavor);
    }

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-----");
        for (int i = 0; i < msgs.length; i++) {

            getLlavor(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34S -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----");
        
    }

    public static void permutaAlfabet() {

        List<Character> lletres = new ArrayList<>();

        for (char c : alfabet  ) {
            lletres.add(c);
        }
        Collections.shuffle(lletres);

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            resultat[i] = lletres.get(i);
        }

    }

    public static char[] getAlfabet() {
        return alfabet;
    }

    public static void setAlfabet(char[] alfabet) {
        Polialfabetic.alfabet = alfabet;
    }

    public static String xifraPoliAlfa(String msg) {



    }

    public static String desxifraPoliAlfa(String msg) {


    }
}