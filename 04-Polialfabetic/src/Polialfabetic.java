import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    static char[] alfabet = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private static char[] permutacio;
    
    private static Random aleatori;

    private static long clauSecreta = 12345;

    private static void initRandom(long clauSecreta) {
        aleatori = new Random(clauSecreta);
    }

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-----");
        for (int i = 0; i < msgs.length; i++) {

            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34S -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----");
        
        for (int i = 0; i < msgsXifrats.length; i++) {

            initRandom(clauSecreta);
            String desxifrat = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i],desxifrat);
        }
    }

    public static void permutaAlfabet() {

        List<Character> lletres = new ArrayList<>();

        for (char lletra : alfabet) {
             lletres.add(lletra);
        }

        Collections.shuffle(lletres, aleatori);
        permutacio = new char[alfabet.length];

        for (int i = 0; i < lletres.size(); i++) {
            permutacio[i] = lletres.get(i);
        }
    }


    private static int buscaPosicio(char[] array, char lletra) {
        for (int i = 0; i < array.length; i++) {

            if (array[i] == lletra) {
                return i;
            }
            
        }

        return -1;
    }


    public static String xifraPoliAlfa(String msg) {

        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {

            char caracterOriginal = msg.charAt(i);

            boolean esMinuscula = Character.isLowerCase(caracterOriginal);

            char lletraMajuscula = Character.toUpperCase(caracterOriginal);

            int posicio = buscaPosicio(alfabet, lletraMajuscula);

            if (posicio != -1) {

                permutaAlfabet();

                char lletraXifrada = permutacio[posicio];

                if (esMinuscula) {
                    lletraXifrada = Character.toLowerCase(lletraXifrada);
                }

                resultat.append(lletraXifrada);
            } else {
                resultat.append(caracterOriginal);
            }
        }

         return resultat.toString();

    }

    public static String desxifraPoliAlfa(String msg) {

        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) { 

            char caracter = msg.charAt(i);

            boolean esMinuscula = Character.isLowerCase(caracter);

            char lletraMajuscula = Character.toUpperCase(caracter);

            int comprovacio = buscaPosicio(alfabet, lletraMajuscula);

            if (comprovacio != -1) {
                permutaAlfabet();

                int posicio = buscaPosicio(permutacio, lletraMajuscula);

                char lletraOriginal = alfabet[posicio];

                if (esMinuscula) {
                    lletraOriginal = Character.toLowerCase(lletraOriginal);
                }

                resultat.append(lletraOriginal);
            } else {
                resultat.append(caracter);
            }
        }
            return resultat.toString();
    }

}
