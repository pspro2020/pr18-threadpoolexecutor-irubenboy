import classes.HandleFixedThreadPool;
import classes.Power;

public class FixedThreadPool {
    private static final int MAX_POWERS = 10;
    public static void main(String[] args) {
        HandleFixedThreadPool handle = new HandleFixedThreadPool();
        for (int i = 0; i < MAX_POWERS; i++) {
            Power p = new Power(i+1);
            handle.execute(p);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
        }

        try {
            handle.shutDown();
        } catch (InterruptedException e) {
            return;
        }
    }
}
