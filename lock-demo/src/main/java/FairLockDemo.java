/**
 * @Classname FairLockDemo
 * @Description TODO
 * @Date 2020/2/25 14:42
 * @Created by CXR
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ƽ���� new ReentranLock(true)
 */
public class FairLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        ReentrantLock reentrantLock = new ReentrantLock(true);

    }
}
