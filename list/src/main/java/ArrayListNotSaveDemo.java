import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 集合类线程不安全的问题
 * 为什么线程不安全：因为 list.add 方法没有synchronized 方法
 * 造成java.util.ConcurrentModificationException（并发修改异常）
 * ArrayList、Map、Set在高并发，多线程下的不安全异常。
 * <p>
 * 解决方法
 * 1、new Vector()
 * 2、Collections.synchronized(new ArrayList<>());
 * 3、CopyOnWriteArrayList ： 写时复制（ConcurrentHashMap）
 */
public class ArrayListNotSaveDemo {
    public static void main(String[] args) {
//        //这是线程不安全的ArrayList
//        ArrayList<String> list = new ArrayList<>();
//        for(int i=1;i<=20;i++){
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                list.forEach(System.out::print);
//            },String.valueOf(i)).start();
//        }
        //这是线程安全的ArrayList
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<String> saveList = Collections.synchronizedList(new ArrayList<>());
        //List<String> saveList = new Vector<>();
        List<String> saveList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                saveList.add(UUID.randomUUID().toString().substring(0, 8));
                for (String str : saveList) {
                    System.out.println(str);
                }
            }, String.valueOf(i)).start();
        }


    }
}
