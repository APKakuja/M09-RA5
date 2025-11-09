package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    
    private char[] permutacio;

    public XifradorMonoalfabetic() {
    
        this.permutacio = permutaAlfabet(maiusculas);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append(substitueix(c, maiusculas, permutacio));
        }
        return new TextXifrat(sb.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String s = new String(xifrat.getBytes());
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(substitueix(c, permutacio, maiusculas));
        }
        return sb.toString();
    }

    // Genera la permutació de l'alfabet 
    private char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            resultat[i] = llista.get(i);
        }
        return resultat;
    }

    // Substitueix segons arrays donats
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
