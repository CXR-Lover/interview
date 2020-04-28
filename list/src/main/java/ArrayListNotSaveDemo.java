import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * �������̲߳���ȫ������
 * Ϊʲô�̲߳���ȫ����Ϊ list.add ����û��synchronized ����
 * ���java.util.ConcurrentModificationException�������޸��쳣��
 * ArrayList��Map��Set�ڸ߲��������߳��µĲ���ȫ�쳣��
 * <p>
 * �������
 * 1��new Vector()
 * 2��Collections.synchronized(new ArrayList<>());
 * 3��CopyOnWriteArrayList �� дʱ���ƣ�ConcurrentHashMap��
 */
public class ArrayListNotSaveDemo {
    public static void main(String[] args) {
//        //�����̲߳���ȫ��ArrayList
//        ArrayList<String> list = new ArrayList<>();
//        for(int i=1;i<=20;i++){
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                list.forEach(System.out::print);
//            },String.valueOf(i)).start();
//        }
        //�����̰߳�ȫ��ArrayList
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
