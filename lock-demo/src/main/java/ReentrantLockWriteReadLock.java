import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class RedisCache {
    public volatile Map<String, Object> map = new HashMap<>();
    public ReentrantReadWriteLock wrlLock = new ReentrantReadWriteLock();

    public void write(String key, Object value) {
        wrlLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wrlLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        wrlLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取" + key);
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wrlLock.readLock().unlock();
        }
    }
}

public class ReentrantLockWriteReadLock {
    public static void main(String[] args) {
        RedisCache redisCache = new RedisCache();
        for (int i = 0; i < 5; i++) {
            final int key = i;
            final int value = i;
            new Thread(() -> {
                redisCache.write(key + "", value);
            }, i + "").start();
        }

        for (int i = 0; i < 5; i++) {
            final int key = i;
            final int value = i;
            new Thread(() -> {
                redisCache.read(key + "");
            }, i + "").start();
        }

    }
}
