package iticbcn.xifratge;

public class TextXifrat {
    private final byte[] dades;

    public TextXifrat(byte[] dades) {
        
        this.dades = dades == null ? new byte[0] : dades.clone();
    }

    public byte[] getBytes() {
        return dades.clone();
    }

    @Override
    public String toString() {
        return new String(dades);
    }
}
