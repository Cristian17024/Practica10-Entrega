public class MisionPosibleMain {

    public static void main(String[] args) {
        Escenario e = new Escenario("Nostromo");

        e.addElemento(new Terricola("Ripley", e, new Posicion(2, 2)));          // Terricola en (2,2)
        e.addElemento(new Extraterrestre("Alien", e, new Posicion(2, 5)));     // Extraterrestre en (2,5)
        e.addElemento(new Roca(e, new Posicion(3, 3)));                         // Roca en (3,3)
        Bomba b = new Bomba(e, new Posicion(4, 4), 1);                          // Bomba en (4,4) con radio 1
        e.addElemento(b);

        System.out.println(e);
        b.explotar();
        System.out.println(e);
    }
}
