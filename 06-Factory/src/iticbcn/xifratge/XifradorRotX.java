package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    static char[] minusculas = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
    static char[] maiusculas = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplazamiento;
        try {
            desplazamiento = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplazamiento < 0 || desplazamiento > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        StringBuilder PalabraNueva = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minusculas.length; j++) {
                        if (minusculas[j] == c) {
                            int lugar = (j + desplazamiento) % minusculas.length;
                            PalabraNueva.append(minusculas[lugar]);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < maiusculas.length; j++) {
                        if (maiusculas[j] == c) {
                            int lugar = (j + desplazamiento) % maiusculas.length;
                            PalabraNueva.append(maiusculas[lugar]);
                            break;
                        }
                    }
                }
            } else {
                PalabraNueva.append(c);
            }
        }
        return new TextXifrat(PalabraNueva.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplazamiento;
        try {
            desplazamiento = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplazamiento < 0 || desplazamiento > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String text = new String(xifrat.getBytes());
        StringBuilder PalabraNueva = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    for (int j = 0; j < minusculas.length; j++) {
                        if (minusculas[j] == c) {
                            int lugar = (j - desplazamiento) % minusculas.length;
                            if (lugar < 0) lugar += minusculas.length;
                            PalabraNueva.append(minusculas[lugar]);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < maiusculas.length; j++) {
                        if (maiusculas[j] == c) {
                            int lugar = (j - desplazamiento) % maiusculas.length;
                            if (lugar < 0) lugar += maiusculas.length;
                            PalabraNueva.append(maiusculas[lugar]);
                            break;
                        }
                    }
                }
            } else {
                PalabraNueva.append(c);
            }
        }
        return PalabraNueva.toString();
    }
}
