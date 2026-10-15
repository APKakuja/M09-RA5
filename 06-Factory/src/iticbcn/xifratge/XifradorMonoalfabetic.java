package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {


    static char[] alfabet = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private static char[] permutacio;

    public XifradorMonoalfabetic() {
        permutacio = permutaAlfabet(alfabet);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }

    private void comprovaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifrador monoalfabètic no suporta clau != null");
        }
    }


    public void main(String[] args) {
        permutacio = permutaAlfabet(alfabet);

        System.out.println("Alfabet original: " + String.valueOf(alfabet));
        System.out.println("Alfabet permutat: " + String.valueOf(permutacio));
        System.out.println(" ");

        String[] exemples = {

            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"

        };

        System.out.println("Xifratge:");
        System.out.println(" ");

        for (String text : exemples) {
            String xifrat = xifraMonoAlfa(text);
            System.out.println(text + " --> " + xifrat);
            System.out.println(" ");

        }

        System.out.println(" ");
        System.out.println("Desxifratge:");
        System.out.println(" ");

        for (String text : exemples) {

            String xifrat = xifraMonoAlfa(text);
            String desxifrat = desxifraMonoAlfa(xifrat);
            System.out.println(xifrat + " --> " + desxifrat);
            System.out.println(" ");

        }
        
    }

    private char[] permutaAlfabet(char[] alfabet) {
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


     private char substitueix(char c, char[] origen, char[] desti) {
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

    public String xifraMonoAlfa(String cadena) {
    
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, alfabet, permutacio));
        }

        return resultat.toString();
    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            resultat.append(substitueix(c, permutacio, alfabet));
        }

        return resultat.toString();
    }
}