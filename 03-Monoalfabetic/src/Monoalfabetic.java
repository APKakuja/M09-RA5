import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Monoalfabetic {
    // Arrays de abecedari maiusculas
    
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    
    public static void main(String[] args) {
        char text;
        String xifrat;
        String desxifrat;
        int desplazamientos; 
        String[] ejemplos = {
                "ABC",
                "XYZ",
                "Hola, Mr. calçot",
                "Perdó, per tu què és?"
        };

    }

    private static char[] permutacio;
// metode per permutar el abecedari

    public static char[] permutaAlfabet(char[] alfabet) {
        
        Collections.shuffle(maiusculas); 
        char[] resultat = new char[maiusculas.length];
        for (int i = 0; i < maiusculas.length; i++) {
            resultat[i] = llista.get(i);
        }
        return resultat;
    }

// Funcció per xifrar

    public static String xifraMonoAlfa(String cadena) {
        return null;
        }
                
// Funcció per desxifrar
    public static String desxifraMonoAlfa(String cadena) {
        return null;
    }

}


        