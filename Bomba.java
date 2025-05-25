public class Bomba extends Elemento implements Destruible {

    private int radio;

    public Bomba(Escenario e, Posicion p, int r) {
        super(e, p);
        this.radio = 1;
    }

    public int getRadio() {
        return radio;
    }

    public void explotar() {
        System.out.println("Explotando bomba!!");
        escenario.destruirElementos(this.posicion, this.radio);
    }

    @Override
    public String destruir() {
        return "Bomba destruida";
    }

    @Override
    public String toString() {
        return "B";
    }
}
