import java.util.Scanner;

public class Rot13 {

    static char[] majuscules = "AÀÁBCÇDEÉÈFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    static char[] minuscules = "aàábcçdeéèfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();

    public static void main(String[] args) {

        Scanner paraula = new Scanner(System.in);
        String text = "";
        String textXifrat = "";
        String textDesxifrat = "";

        System.out.println("Introdueix text per xifrar");
        text = paraula.nextLine();
        textXifrat = xifraRot13(text);
        System.out.println(textXifrat + " text cifrat");
        textDesxifrat = desxifraRot13(textXifrat);
        System.out.println(textDesxifrat + " text descifrat");
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