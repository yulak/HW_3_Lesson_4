package hw_3_4;

public class Main {
    // Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.

    volatile int status = 0;

    public static void main(String[] args) {
        Main lock = new Main();
        Thread t1 = new Thread(new Task("A", lock, 1));
        Thread t2 = new Thread(new Task("B", lock, 1));
        Thread t3 = new Thread(new Task("C", lock, 1));

        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable {
        private String message;
        private final Main lock;
        private int i;

        public Task(String word, Main obj, int i) {
            this.message = word;
            this.lock = obj;
            this.i = i;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
            while (lock.status < 1) {
                synchronized (lock) {
                    while (!((lock.status % 3) == 0) && i == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    while (!((lock.status % 3) == 0) && i == 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                    while (!((lock.status % 3) == 0) && i == 3) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(message);
                    lock.status++;
                    if ((lock.status % 3) == 0) {
                        System.out.println("=");
                        lock.notifyAll();
                    }
                }
            }
        }
    }
}
