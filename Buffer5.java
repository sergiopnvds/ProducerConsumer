package productor_consumidor;

import java.io.*;

public class Buffer5<E>
        implements Buffer<E> {
    private volatile PipedOutputStream pos;
    private volatile PipedInputStream pis;
    private volatile ObjectOutputStream oos;
    private volatile ObjectInputStream ois;

    public Buffer5(int size) {
        try {
            pos = new PipedOutputStream();
            pis = new PipedInputStream(pos);
        } catch (IOException e) {
            System.err.println("new Buffer: " + e);
        }
    }

    public void put(E x) {
        try {
            if (oos == null)
                oos = new ObjectOutputStream(pos);
            oos.writeObject(x);
        } catch (IOException e) {
            System.err.println("Buffer.put " + e);
        }
    }

    public E get() {
        try {
            if (ois == null)
                ois = new ObjectInputStream(pis);
            return (E) ois.readObject();
        } catch (Exception ioe) {
            System.err.println("Buffer.get " + ioe);
            return null;
        }
    }
}
