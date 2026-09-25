
public class RotX {
 
    static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static char[] minuscules = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz".toCharArray();

    public static void main(String[] args) {
        String text;
        String xifrat;
        String desxifrat;
        String[] ejemplos = {
                "ABC",
                "XYZ",
                "Hola, Mr. calçot",
                "Perdó, per tu què és?"
        };

        int[] desplazamientos = {0, 2, 4, 6};

        System.out.println("Xifrat");


        for (int i = 0; i < ejemplos.length; i++) {

            text = ejemplos[i];
            int d = desplazamientos[i];
            xifrat = xifraRotX(text, d);
            System.out.println("(" + d + ")-" + text + " => " + xifrat);

        }

        System.out.println("Desxifrat");

        for (int i = 0; i < ejemplos.length; i++) {
            xifrat = xifraRotX(ejemplos[i], desplazamientos[i]);
            desxifrat = desxifraRotX(xifrat, desplazamientos[i]);

            System.out.println("(" + desplazamientos[i] + ")-" + xifrat + " => " + desxifrat);

        }

        xifrat = xifraRotX("Perdó, per tu què és?", 6);
        System.out.println("\nMissatge xifrat: " + xifrat);

        for (int g = 0; g < minuscules.length; g++) {
            desxifrat = desxifraRotX(xifrat, g);
            System.out.println("(" + g + ")->" + desxifrat);
        }

         System.out.println("Força bruta:");
         forcaBrutaRotX(xifrat);
    }

     public static String xifraRotX(String text, int desplazamiento) {
        String PalabraNueva = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minuscules.length; j++) {
                        if (minuscules[j] == c) {
                            int lugar = (j + desplazamiento) % minuscules.length;
                            PalabraNueva += minuscules[lugar];
                        }
                    }
                } else {
                    for (int j = 0; j < majuscules.length; j++) {
                        if (majuscules[j] == c) {
                            int lugar = (j + desplazamiento) % majuscules.length;
                            PalabraNueva += majuscules[lugar];
                        }
                    }
                }
            } else {
                PalabraNueva += c;
            }
        }
        return PalabraNueva;
    }

    public static String desxifraRotX(String text, int desplazamiento) {
        String PalabraNueva = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minuscules.length; j++) {
                        if (minuscules[j] == c) {
                            int lugar = (j - desplazamiento) % minuscules.length;
                            if (lugar < 0) lugar += minuscules.length;
                            PalabraNueva += minuscules[lugar];
                        }
                    }
                } else {
                    for (int j = 0; j < majuscules.length; j++) {
                        if (majuscules[j] == c) {
                            int lugar = (j - desplazamiento) % majuscules.length;
                            if (lugar < 0) lugar += majuscules.length;
                            PalabraNueva += majuscules[lugar];
                        }
                    }
                }
            } else {
                PalabraNueva += c;
            }
        }
        return PalabraNueva;
     }
     
      public static void forcaBrutaRotX(String text) {
        for (int d = 0; d < minuscules.length; d++) {
            String intento = desxifraRotX(text, d);
            System.out.println("(" + d + ") -> " + intento);
        }
    }
}