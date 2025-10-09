import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    // Array del abecedari en maiusculas
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    // Array amb la permutació
    private static char[] permutacio;

    public static void main(String[] args) {
        // Generar i mostras la permutació
        permutacio = permutaAlfabet(maiusculas);

        System.out.println("Alfabet original: " + String.valueOf(maiusculas));
        System.out.println("Alfabet permutat: " + String.valueOf(permutacio));

        // Exemples
        String[] ejemplos = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        for (String text : ejemplos) {
            String xifrat = xifraMonoAlfa(text);
            String desxifrat = desxifraMonoAlfa(xifrat);

            System.out.println("\nText original:   " + text);
            System.out.println("Text xifrat:     " + xifrat);
            System.out.println("Text desxifrat:  " + desxifrat);
        }
    }

    // Genera la permutació de l'alfabet
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        Collections.shuffle(llista); // Barreja de manera aleatoria

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            resultat[i] = llista.get(i);
        }
        return resultat;
    }

    // Xifra el text
    public static String xifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, maiusculas, permutacio));
        }

        return resultat.toString();
    }

    // Desxifra el text
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, permutacio, maiusculas));
        }

        return resultat.toString();
    }

    private static char substitueix(char c, char[] origen, char[] desti) {
        boolean esMinuscula = Character.isLowerCase(c);
        char majuscula = Character.toUpperCase(c);

        // Busca el character en el array origen
        for (int i = 0; i < origen.length; i++) {
            if (origen[i] == majuscula) {
                char substitut = desti[i];

                // Si esta en minuscula retorna en maiuscula
                if (esMinuscula) {
                    return Character.toLowerCase(substitut);
                } else {
                    return substitut;
                }
            }
        }

        // Si no es una lletra es retorna igual
        return c;
    }
}
