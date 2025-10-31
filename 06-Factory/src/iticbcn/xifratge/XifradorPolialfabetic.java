package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;

public class XifradorPolialfabetic {
static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private static char[] permutacio;

    private static Random aleatori;

    public static void main(String[] args) {

        String msgs[] = {
            "Test 01 àrbitre, coixí, Perimetre",
            "Test 02 Taull, DÍA, año",
            "Test 03 Peça, Òrrius, Bòlivia"
        };
        String msgsXifrats[] = new String[msgs.length];

        String clauSecreta = "Hola123";

        initRandom(clauSecreta);

        // Generem la primera permutació
        permutacio = permutaAlfabet(maiusculas);

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i], msgs[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    
    private static void initRandom(String clauSecreta) {
        long contrasenya = clauSecreta.hashCode();
        aleatori = new Random(contrasenya);
    }

   
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        
        Collections.shuffle(llista, aleatori);

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            resultat[i] = llista.get(i);
        }

        return resultat;
    }

    // 🔹 Xifra el text
    public static String xifraPoliAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, maiusculas, permutacio));
        }

        return resultat.toString();
    }

    public static String desxifraPoliAlfa(String cadena, String msg) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, permutacio, maiusculas));
        }

        return resultat.toString();
    }

    // 🔹 Substitueix una lletra d'un alfabet a un altre
    private static char substitueix(char c, char[] origen, char[] desti) {
        boolean esMinuscula = Character.isLowerCase(c);
        char majuscula = Character.toUpperCase(c);

        // Busca la lletra en l'alfabet origen
        for (int i = 0; i < origen.length; i++) {
            if (origen[i] == majuscula) {
                char substitut = desti[i];

                // Retorna en majúscula o minúscula segons correspongui
                if (esMinuscula) {
                    return Character.toLowerCase(substitut);
                } else {
                    return substitut;
                }
            }
        }

        // Si no és una lletra, la deixa igual
        return c;
    }
}
