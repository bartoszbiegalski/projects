import java.util.*;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private int id;
    private int meals;
    private int philosophersNumber;

    private int []C;

    private Random random = new Random();

    private Semaphore mutex;
    private Semaphore []prisem;


    public Philosopher(int id, int meals, int philosophersNumber, int []C, Semaphore mutex) {
        this.id = id;
        this.meals = meals;
        this.philosophersNumber = philosophersNumber;

        this.C = new int[philosophersNumber];

        this.mutex = mutex;
        this.prisem = new Semaphore[philosophersNumber];
        for (int i = 0; i < philosophersNumber; i++) {
            prisem[i] = new Semaphore(1, true);
        }
    }

    private void test(int id) {
        try {
            if (C[(id-1+philosophersNumber)%philosophersNumber] != 2 && C[id] == 1 && C[(id+1+philosophersNumber)%philosophersNumber] != 2) {
                C[id] = 2;
                prisem[id].acquire();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void run() {
        for (int i = 0; i < meals; i++) {
            try {
                mutex.acquire();
                C[id] = 1;
                test(id);
                mutex.release();

                prisem[id].release();

                mutex.acquire();
                System.out.println("Philosopher number " + id + " has eaten his "  + (i + 1) + " meal");
                C[id] = 0;
                test((id-1+philosophersNumber)%philosophersNumber);
                test((id+1+philosophersNumber)%philosophersNumber);
                mutex.release();
                Thread.sleep(random.nextInt(10));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

