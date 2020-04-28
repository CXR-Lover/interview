/**
 * @Classname FairLockDemo
 * @Description TODO
 * @Date 2020/2/25 14:42
 * @Created by CXR
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 new ReentranLock(false) �� synchronized
 */
public class NonFairLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
    }
}
