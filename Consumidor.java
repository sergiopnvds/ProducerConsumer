package productor_consumidor;

import java.util.Random;

public class Consumidor extends Thread {
    private final Random random = new Random();
    private final Buffer<String> buffer;
    private volatile boolean parado = true;
    private final LogWindow logWindow;

    public Consumidor(String id, Buffer<String> buffer) {
        setName(id);
        this.buffer = buffer;
        logWindow = new LogWindow(this);
    }

    public void run() {
        try {
            while (true) {
                while (parado)
                    Thread.sleep(1000);

                String msg = buffer.get();
                logWindow.println("Consume " + msg);
                Thread.sleep(random.nextInt(2) * 1000);
            }
        } catch (InterruptedException e) {
            logWindow.println(e.toString());
        }
        logWindow.println("fin");
    }

    public void parar() { parado = true; }

    public void arrancar() { parado = false; }
}
