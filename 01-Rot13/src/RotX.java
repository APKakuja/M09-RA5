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
        String[] ejemplos = {
                "ABC",
                "XYZ",
                "Hola, Mr. calçot",
                "Perdó, per tu què és?"
        };

        //Desplazamientos per casos de proba automatic, tal com s'indica al anunciat
        int[] desplazamientos = {0, 2, 4, 6};


        System.out.println("Xifrat");
        System.out.println("------");
        for (int i = 0; i < ejemplos.length; i++) {
            text = ejemplos[i];
            int d = desplazamientos[i];
            xifrat = xifraRotX(text, d);
            System.out.println("(" + d + ")-" + text + " => " + xifrat);
        }

        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < ejemplos.length; i++) {
            xifrat = xifraRotX(ejemplos[i], desplazamientos[i]);
            desxifrat = desxifraRotX(xifrat, desplazamientos[i]);
            System.out.println("(" + desplazamientos[i] + ")-" + xifrat + " => " + desxifrat);
        }

        xifrat = xifraRotX("Perdó, per tu què és?", 6);
        System.out.println("\nMissatge xifrat: " + xifrat);
        System.out.println("----------------");

        //Utilitza el array minusculas per els numeros de desplazament totals
        for (int g = 0; g < minusculas.length; g++) {
            desxifrat = desxifraRotX(xifrat, g);
            System.out.println("(" + g + ")->" + desxifrat);
        }

        // Demanem casos de prova
        System.out.println("\nIntrodueix un text per xifrar:");
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
