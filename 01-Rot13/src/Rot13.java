
import java.util.Scanner;

public class Rot13 {
// Creació dels dos arrays de abecedari tan em minuscula com maiuscula
     static char[] minusculas = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
     static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        Scanner prova = new Scanner(System.in);
        String text = "";
        String xifrat = "";
        String desxifrat = " ";

        System.out.println("Introdueix un text per xifrar:");
        text = prova.nextLine();


        xifrat = xifraRot13(text);
        System.out.println(xifrat + " text cifrat");
        desxifrat = desxifraRot13(xifrat);
        System.out.println(desxifrat + " text descifrat");

    }
//Funcio per xifrar
public static String xifraRot13(String text) {
    int largo = text.length();
    int condicion = largo;
    char c = ' ';
    int lugar = 0;
    String PalabraNueva = "";
//Bucle while dura mentres que el largo de la paraula sigui mes gran que 0
while (condicion > 0) {
//Comprueba si el caracter es una lletra i si la lletra es minuscula una per una 
//i si es minuscula la cambía per 13 posicions mes endavant i guarda en la nova paraula 
    for (int i = 0; i < largo; i++) {
        c = text.charAt(i);
        if (Character.isLetter(c)) {       
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < minusculas.length; j++) {
                    if (minusculas[j] == c) {
                        lugar = j + 13;
                        if (lugar >= minusculas.length) {
                            lugar = lugar - minusculas.length;
                            PalabraNueva = PalabraNueva + minusculas[lugar];
                        } else {
                            PalabraNueva = PalabraNueva + minusculas[lugar];
                        }
                    }
                }
            } else {
                for (int o = 0; o < maiusculas.length; o++) {
                    if (maiusculas[o] == c) {
                        lugar = o + 13;
                        if (lugar >= maiusculas.length) {
                            lugar = lugar - maiusculas.length;
                            PalabraNueva = PalabraNueva + maiusculas[lugar];
                        } else {
                            PalabraNueva = PalabraNueva + maiusculas[lugar];
                        }
                    }
                }
            }
        } else {
            PalabraNueva = PalabraNueva + c;
        }
        condicion--;
    }
}
return PalabraNueva;
}
//Funcio per descifrar
public static String desxifraRot13 (String text) {
    int largo = text.length();
    int condicion = largo;
    char c = ' ';
    int lugar = 0;
    String PalabraNueva = "";
//Bucle while dura mentres que el largo de la paraula sigui mes gran que 0
    while (condicion > 0) {
//Comprueba si el caracter es una lletra i si la lletra es minuscula una per una
//i si es minuscula la cambía per 13 posicions abans per descifrar i guarda en la nova paraula
        for (int i = 0; i < largo; i++) {
            c = text.charAt(i);
            if (Character.isLetter(c)) {

                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minusculas.length; j++) {
                        if (minusculas[j] == c) {
                            lugar = j - 13;
                            if (lugar < 0 ) {
                                lugar = lugar + minusculas.length;
                                PalabraNueva = PalabraNueva + minusculas[lugar];
                            } else {
                                PalabraNueva = PalabraNueva + minusculas[lugar];
                            }
                        }
                    }
                } else {
                    for (int o = 0; o < maiusculas.length; o++) {
                        if (maiusculas[o] == c) {
                            lugar = o - 13;
                            if (lugar <= 0) {
                                lugar = lugar + maiusculas.length;
                                PalabraNueva = PalabraNueva + maiusculas[lugar];
                            } else {
                                PalabraNueva = PalabraNueva + maiusculas[lugar];
                            }
                        }
                    }
                }
            } else {
                PalabraNueva = PalabraNueva + c;
            }
            condicion--;
        }
    }
    return PalabraNueva;
}


}
