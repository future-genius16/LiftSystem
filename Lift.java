import java.util.concurrent.BlockingQueue;

public class Lift extends Thread {
    private final BlockingQueue<Query> queries;
    private int currentFloor = 1;
    private Query query = null;

    public Lift(String name, BlockingQueue<Query> queries) {
        super(name);
        this.queries = queries;
    }


    @Override
    public void run() {
        try {
            while (true) {
                if (query == null) {
                    query = queries.take();
                }
                if (query.getDestination() > currentFloor) {
                    Thread.sleep(1000);
                    currentFloor++;
                    System.out.println(getName() + ": " + (currentFloor - 1) + " -> " + currentFloor);
                } else if (query.getDestination() < currentFloor) {
                    Thread.sleep(1000);
                    currentFloor--;
                    System.out.println(getName() + ": " + (currentFloor + 1) + " -> " + currentFloor);
                } else {
                    Thread.sleep(250);
                    System.out.println(getName() + ": Doors opened");
                    Thread.sleep(500);
                    System.out.println(getName() + ": Doors closed");
                    if (query.isStarted()) {
                        if (query.getDestination() > 1) {
                            query = new Query(query.getDestination(), 1);
                        } else {
                            query = null;
                        }
                    } else {
                        query.start();
                        System.out.println(getName() + ": Starts " + query.getDescription());
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}