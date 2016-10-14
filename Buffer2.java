package productor_consumidor;

import java.util.concurrent.Semaphore;

public class Buffer2<E>
        implements Buffer<E> {
    private final E[] data;
    private int nDatos;

    private final Semaphore haySitio;
    private final Semaphore hayDatos;

    public Buffer2(int size) {
        data = (E[]) new Object[size];
        nDatos = 0;
        haySitio = new Semaphore(size);
        hayDatos = new Semaphore(0);
    }

    public void put(E x)
            throws InterruptedException {
        haySitio.acquire();
        synchronized (data) {
            data[nDatos++] = x;
            print();
        }
        hayDatos.release();
    }

    public E get()
            throws InterruptedException {
        hayDatos.acquire();
        E x;
        synchronized (data) {
            nDatos--;
            x = data[0];
            System.arraycopy(data, 1, data, 0, nDatos);
            data[nDatos] = null;
            print();
        }
        haySitio.release();
        return x;
    }

    private void print() {
        for (int i = 0; i < nDatos; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
}
