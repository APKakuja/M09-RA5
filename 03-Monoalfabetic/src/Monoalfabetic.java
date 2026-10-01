import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {


    static char[] alfabet = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private static char[] permutacio;


    public static void main(String[] args) {
        permutacio = permutaAlfabet(alfabet);

        System.out.println("Alfabet original: " + String.valueOf(alfabet));
        System.out.println("Alfabet permutat: " + String.valueOf(permutacio));

        String[] exemples = {

            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"

        };

        for (String text : exemples) {
            String xifrat = xifraMonoAlfa(text);
            String desxifrat = desxifraMonoAlfa(xifrat);

            System.out.println("Xifratge:");
            System.out.println(text + " --> " + xifrat);
            System.out.println(" ");
            System.out.println("Desxifratge:");
            System.out.println(xifrat + " --> " + desxifrat);
            System.out.println(" ");
        }
    }

    static char[] permutaAlfabet(char[] alfabet) {
        List<Character> lletres = new ArrayList<>();

        for (char c : alfabet  ) {
            lletres.add(c);
        }
        Collections.shuffle(lletres);

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            resultat[i] = lletres.get(i);
        }
        return resultat;
    }


     private static char substitueix(char c, char[] origen, char[] desti) {
        boolean esMinuscula = Character.isLowerCase(c);
        char majuscula = Character.toUpperCase(c);

        for (int i = 0; i < origen.length; i++) {
            if (origen[i] == majuscula) {
                char substitut = desti[i];

                if (esMinuscula) {
                    return Character.toLowerCase(substitut);
                } else {
                    return substitut;
                }
            }
        }
        return c;
    }

    public static String xifraMonoAlfa(String cadena) {
    
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, alfabet, permutacio));
        }

        return resultat.toString();
    }

    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, permutacio, alfabet));
        }

        return resultat.toString();
    }
}