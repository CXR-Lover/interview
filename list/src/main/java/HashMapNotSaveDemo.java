import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname HashMapNotSaveDemo
 * @Description TODO
 * @Date 2020/2/26 10:04
 * @Created by CXR
 */
public class HashMapNotSaveDemo {

    Map<Long, String> stringMap = new HashMap<>();
    Map<Long, String> longStringMap = new Hashtable<>();
    Map<Long, String> treeMap = new TreeMap<>();
    Map<Long, String> map = new ConcurrentHashMap<>();


    public void addWithHashMap() {
        stringMap.put(Thread.currentThread().getId(), UUID.randomUUID().toString().substring(0, 8));
    }

    public void addWithHashTable() {
        longStringMap.put(Thread.currentThread().getId(), UUID.randomUUID().toString().substring(0, 8));
    }

    public static void main(String[] args) {
        HashMapNotSaveDemo hashMapNotSaveDemo = new HashMapNotSaveDemo();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                hashMapNotSaveDemo.addWithHashMap();
                System.out.println(hashMapNotSaveDemo.toString());
            }, "ThreadName").start();
        }

    }
}
