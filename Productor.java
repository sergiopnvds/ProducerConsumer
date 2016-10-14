package productor_consumidor;

import java.util.Random;

public class Productor extends Thread {
    private final Random random = new Random();
    private final Buffer<String> buffer;
    private int n;
    private volatile boolean parado = true;
    private LogWindow logWindow;

    public Productor(String id, Buffer<String> buffer, int n0) {
        setName(id);
        this.buffer = buffer;
        this.n = n0;
        logWindow = new LogWindow(this);
    }

    public void run() {
        while (true) {
            try {
                while (parado)
                    Thread.sleep(1000);

                String msg = String.valueOf(n++);
                logWindow.println("Produce: " + msg);
                buffer.put(msg);
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                logWindow.println(e.toString());
            }
        }
    }

    public void parar() { parado = true; }

    public void arrancar() { parado = false; }
}

