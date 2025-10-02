import java.util.Scanner;

public class RotX {
    // Arrays de letras minuscules i maiusculas
    static char[] minusculas = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    public static void main(String[] args) {
        Scanner prova = new Scanner(System.in);
        String text;
        String xifrat;
        String desxifrat;

        // Demanem el text
        System.out.println("Introdueix un text per xifrar:");
        text = prova.nextLine();

        // Demanem el desplazamiento
        System.out.println("Introdueix el desplaçament (nombre enter):");
        int desplazamiento = prova.nextInt();

        // Xifra el text
        xifrat = xifraRotX(text, desplazamiento);
        System.out.println("\nText cifrat: " + xifrat);

        // Desxifra el text
        desxifrat = desxifraRotX(xifrat, desplazamiento);
        System.out.println("Text descifrat: " + desxifrat);

        // Probem la força brutax
        System.out.println("\nForça bruta:");
        forcaBrutaRotX(xifrat);
    }

    // Funció para xifrar amb desplazamiento variable
    public static String xifraRotX(String text, int desplazamiento) {
        String PalabraNueva = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minusculas.length; j++) {
                        if (minusculas[j] == c) {
                            int lugar = (j + desplazamiento) % minusculas.length;
                            PalabraNueva += minusculas[lugar];
                        }
                    }
                } else {
                    for (int j = 0; j < maiusculas.length; j++) {
                        if (maiusculas[j] == c) {
                            int lugar = (j + desplazamiento) % maiusculas.length;
                            PalabraNueva += maiusculas[lugar];
                        }
                    }
                }
            } else {
                PalabraNueva += c; // Si no son lletres es mantenen igual
            }
        }
        return PalabraNueva;
    }

    // Funció per desxifrar amb desplazamiento variable
    public static String desxifraRotX(String text, int desplazamiento) {
        String PalabraNueva = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minusculas.length; j++) {
                        if (minusculas[j] == c) {
                            int lugar = (j - desplazamiento) % minusculas.length;
                            if (lugar < 0) lugar += minusculas.length;
                            PalabraNueva += minusculas[lugar];
                        }
                    }
                } else {
                    for (int j = 0; j < maiusculas.length; j++) {
                        if (maiusculas[j] == c) {
                            int lugar = (j - desplazamiento) % maiusculas.length;
                            if (lugar < 0) lugar += maiusculas.length;
                            PalabraNueva += maiusculas[lugar];
                        }
                    }
                }
            } else {
                PalabraNueva += c;
            }
        }
        return PalabraNueva;
    }

    // Funció de força brutaX
    public static void forcaBrutaRotX(String text) {
        for (int d = 1; d <= minusculas.length; d++) {
            String intento = desxifraRotX(text, d);
            System.out.println("(" + d + ") -> " + intento);
        }
    }
}
