package productor_consumidor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer4<E>
        implements Buffer<E> {
    private final BlockingQueue<E> queue;

    public Buffer4(int size) {
        queue = new LinkedBlockingQueue<E>(size);
    }

    public void put(E x)
            throws InterruptedException {
        queue.put(x);
    }

    public E get()
            throws InterruptedException {
        return queue.take();
    }
}
