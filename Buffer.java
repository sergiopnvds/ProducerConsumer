package productor_consumidor;

/**
 * @author Jose A. Manas
 * @version 12/3/2012
 */
public interface Buffer<E> {
    void put(E x)
            throws InterruptedException;

    E get()
            throws InterruptedException;
}
