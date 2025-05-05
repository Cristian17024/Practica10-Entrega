public abstract class Personaje extends Elemento implements Destruible {
    protected String nombre;
    

    public Personaje(String nombre, Escenario e, Posicion p) {
        super(e, p);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String destruir() {
        return this.nombre + " destruido";
    }
}
