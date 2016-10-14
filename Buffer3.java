package productor_consumidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer3<E>
        implements Buffer<E> {
    private final E[] data;
    private int nDatos;

    private final Lock llave;
    private final Condition haySitio;
    private final Condition hayDatos;

    public Buffer3(int size) {
        data = (E[]) new Object[size];
        nDatos = 0;
        llave = new ReentrantLock();
        hayDatos = llave.newCondition();
        haySitio = llave.newCondition();
    }

    public void put(E x)
            throws InterruptedException {
        llave.lock();
        try {
            while (nDatos >= data.length)
                haySitio.await();
            data[nDatos++] = x;
            print();
            hayDatos.signal();
        } finally {
            llave.unlock();
        }
    }

    public E get()
            throws InterruptedException {
        llave.lock();
        try {
            while (nDatos <= 0)
                hayDatos.await();
            nDatos--;
            E x = data[0];
            System.arraycopy(data, 1, data, 0, nDatos);
            data[nDatos] = null;
            print();
            haySitio.signal();
            return x;
        } finally {
            llave.unlock();
        }
    }

    private void print() {
        for (int i = 0; i < nDatos; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
}
