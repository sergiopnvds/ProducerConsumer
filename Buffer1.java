package productor_consumidor;

public class Buffer1<E>
        implements Buffer<E> {
    private final E[] data;
    private int nDatos;

    public Buffer1(int size) {
        data = (E[]) new Object[size];
        nDatos = 0;
    }

    public synchronized void put(E x)
            throws InterruptedException {
        while (nDatos >= data.length)
            wait();
        data[nDatos++] = x;
        print();
        notifyAll();
    }

    public synchronized E get()
            throws InterruptedException {
        while (nDatos <= 0)
            wait();
        nDatos--;
        E x = data[0];
        System.arraycopy(data, 1, data, 0, nDatos);
        data[nDatos] = null;
        print();
        notifyAll();
        return x;
    }

    private void print() {
        for (int i = 0; i < nDatos; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
}
