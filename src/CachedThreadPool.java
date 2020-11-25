import classes.HandleCachedThreadPool;
import classes.Power;

public class CachedThreadPool {
    private static final int MAX_POWERS = 10;
    public static void main(String[] args) {
        HandleCachedThreadPool handle = new HandleCachedThreadPool();
        for (int i = 0; i < MAX_POWERS; i++) {
            Power power = new Power(i+1);
            handle.execute(power);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }

        try {
            handle.shoutDown();
        } catch (InterruptedException e) {
            return;
        }
    }
}
