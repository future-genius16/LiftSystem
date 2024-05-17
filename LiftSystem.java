import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class LiftSystem {
    private static int floors = 10;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Query> queries = new ArrayBlockingQueue<>(100);

        Lift lift1 = new Lift("Lift 1", queries);
        Lift lift2 = new Lift("Lift 2", queries);
        Lift lift3 = new Lift("Lift 3", queries);

        Random random = new Random();

        lift1.start();
        lift2.start();
        lift3.start();

        while (true) {
            int floor = random.nextInt(floors-1) + 2;
            if (random.nextInt() % 2 == 0) {
                queries.put(new Query(1, floor));
            } else {
                queries.put(new Query(floor, 1));
            }
            Thread.sleep(10000);
        }
    }
}



