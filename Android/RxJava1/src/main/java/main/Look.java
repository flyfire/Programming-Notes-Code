package main;

public class Look {

    public static void look() {
        synchronized (Look.class) {
            try {
                Look.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
