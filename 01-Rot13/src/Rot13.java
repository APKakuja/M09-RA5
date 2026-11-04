
public class Rot13 {

    static char[] majuscules = "AÀÁBCÇDEÉÈFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    static char[] minuscules = "aàábcçdeéèfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();

    public static void main(String[] args) {

        String[] textos = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        System.out.println("Xifrat");
        System.out.println("--------");
        for (String text : textos) {
            System.out.println(text + " => " + xifraRot13(text));
            
        }

        System.out.println(" ");
        System.out.println("Desxifrat");
        System.out.println("--------");
        for (String text : textos) {
            String textXifrat = xifraRot13(text);
            System.out.println(textXifrat + " => " + desxifraRot13(textXifrat));
        }
    }

    public static String xifraRot13(String text) {

        int llarg = text.length();
        int condicio = llarg;
        char c = ' ';
        int lloc = 0;
        String novaParaula = "";

                while (condicio > 0) {
                    for ( int i = 0; i< llarg; i++) {
                        c=text.charAt(i);
                        
                        if (Character.isLetter(c)) {
                            if (Character.isLowerCase(c)) {
                                for (int j = 0; j < minuscules.length; j++) {
                                    if (minuscules[j] == c) {
                                        lloc = j + 13;
                                        if (lloc >= minuscules.length) {
                                            lloc = lloc - minuscules.length;
                                            novaParaula = novaParaula+minuscules[lloc];
                                        } else 
                                        {
                                            novaParaula = novaParaula + minuscules[lloc];
                                        }
                                    }
                                }
                            } else 
                            {
                                for (int o = 0; o < majuscules.length; o++) {
                            if (majuscules[o] == c) {
                                lloc = o + 13;
                                if (lloc >= majuscules.length) {
                                    lloc = lloc - majuscules.length;
                                    novaParaula = novaParaula + majuscules[lloc];
                                } else {
                                    novaParaula = novaParaula + majuscules[lloc];
                                }
                            }
                        }
                    }
                } else {
                    novaParaula = novaParaula + c;
                }
                condicio--;
            }
        } return novaParaula;
    } 

    public static String desxifraRot13(String text) {
        int llarg = text.length();
        int condicio = llarg;
        char c = ' ';
        int lloc = 0;
        String novaParaula = "";
            while (condicio > 0) {
                for (int i = 0; i < llarg; i++) {
                    c = text.charAt(i);
                    if (Character.isLetter(c)) {

                        if (Character.isLowerCase(c)) {
                            for (int j = 0; j < minuscules.length; j++) {
                                if (minuscules[j] == c) {
                                    lloc = j - 13;
                                    if (lloc < 0 ) {
                                        lloc = lloc + minuscules.length;
                                        novaParaula = novaParaula + minuscules[lloc];
                                    } else {
                                        novaParaula = novaParaula + minuscules[lloc];
                                    }
                                }
                            }
                        } else {
                            for (int o = 0; o < majuscules.length; o++) {
                                if (majuscules[o] == c) {
                                    lloc = o - 13;
                                    if (lloc < 0) {
                                        lloc = lloc + majuscules.length;
                                        novaParaula = novaParaula + majuscules[lloc];
                                    } else {
                                        novaParaula = novaParaula + majuscules[lloc];
                                    }
                                }
                            }
                        }
                    } else {
                        novaParaula = novaParaula + c;
                    }
                    condicio--;
                }
            }
            return novaParaula;
        }
}