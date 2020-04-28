/**
 * @Classname FairLockDemo
 * @Description TODO
 * @Date 2020/2/25 14:42
 * @Created by CXR
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁： new ReentranLock() 和 Synchronized
 * 他们本身就是
 */
class Demo {
    Lock lock = new ReentrantLock();
}

public class ReenTranLockDemo {
    public static void main(String[] args) {

    }
}
