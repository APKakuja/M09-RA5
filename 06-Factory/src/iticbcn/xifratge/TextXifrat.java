package iticbcn.xifratge;

public class TextXifrat {
    private final byte[] dades;

    public TextXifrat(byte[] dades) {
        if (dades == null) {
            this.dades = new byte[0];
        } else {
            this.dades = new byte[dades.length];
            for (int i = 0; i < dades.length; i++) {
                this.dades[i] = dades[i];
            }
        }
    }

    public byte[] getBytes() {
        byte[] copia = new byte[dades.length];
        for (int i = 0; i < dades.length; i++) {
            copia[i] = dades[i];
        }
        return copia;
    }

    @Override
    public String toString() {
        return new String(dades);
    }
}
