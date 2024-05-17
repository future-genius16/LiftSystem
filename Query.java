public class Query {
    private final int start;
    private final int end;

    private boolean started;

    public Query(int start, int end) {
        this.start = start;
        this.end = end;
        System.out.println("New " + getDescription());
    }

    public int getDestination() {
        if (started) {
            return end;
        } else {
            return start;
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        this.started = true;
    }

    public String getDescription(){
        return "query: " + start + " -> " + end;
    }
}