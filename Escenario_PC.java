package productor_consumidor;

public class Escenario_PC {
    public static void main(String[] args) {
//        Buffer1<String> buffer = new Buffer1<String>(3);
        Buffer<String> buffer = new Buffer2<String>(3);
//        Buffer<String> buffer = new Buffer3<String>(3);
//        Buffer<String> buffer = new Buffer4<String>(3);
//        Buffer<String> buffer = new Buffer5<String>(3);
        Thread productor1 = new Productor("p1", buffer, 1000);
        Thread productor2 = new Productor("p2", buffer, 2000);
        Thread consumidor = new Consumidor("c1", buffer);

        productor1.start();
        productor2.start();
        consumidor.start();
    }
}
