import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int philosophersNumber = Integer.parseInt(args[0]);
        int mealsNumber = Integer.parseInt(args[1]);

        int []C = new int[philosophersNumber];

        Semaphore mutex = new Semaphore(1, true);
        Object []prisem = new Object[philosophersNumber];
        Philosopher []philosopher = new Philosopher[philosophersNumber];



        for (int i = 0; i < philosophersNumber; i++) {
            philosopher[i] = new Philosopher(i, mealsNumber, philosophersNumber, C, mutex);
        }

        for (int i = 0; i < philosophersNumber; i++) {
            philosopher[i].start();
        }
    }
}
