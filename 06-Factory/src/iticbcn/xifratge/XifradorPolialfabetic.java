package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    private char[] permutacio;
    private Random aleatori;

    public XifradorPolialfabetic() {
        
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        this.permutacio = permutaAlfabet(maiusculas);

        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append(substitueix(c, maiusculas, permutacio));
        }
        return new TextXifrat(sb.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        this.permutacio = permutaAlfabet(maiusculas);

        String s = new String(xifrat.getBytes());
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(substitueix(c, permutacio, maiusculas));
        }
        return sb.toString();
    }

    private void initRandom(long seed) {
        this.aleatori = new Random(seed);
    }

    private char[] permutaAlfabet(char[] alfabet) {
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

    private char substitueix(char c, char[] origen, char[] desti) {
        boolean esMinuscula = Character.isLowerCase(c);
        char majuscula = Character.toUpperCase(c);

        for (int i = 0; i < origen.length; i++) {
            if (origen[i] == majuscula) {
                char substitut = desti[i];
                return esMinuscula ? Character.toLowerCase(substitut) : substitut;
            }
        }
        return c;
    }
}
