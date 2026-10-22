package iticbcn.xifratge;

import java.security.KeyPair;

public class Main {
    public static void main(String[] args) throws Exception {
        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar áéíóú àèìòù";

        KeyPair parellClaus = cp.generaClausRSA();
    }
}