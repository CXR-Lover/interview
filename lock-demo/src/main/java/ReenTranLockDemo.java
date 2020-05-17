class Person implements Runnable {

    public synchronized void work() throws Exception {
        System.out.println(Thread.currentThread().getName() + " work ");
        run(Thread.currentThread().getId());
    }

    public synchronized void run(long id) throws Exception {
        if (id != Thread.currentThread().getId()) {
            throw new Exception("ÎÊÌâ");
        }
        System.out.println(Thread.currentThread().getName() + " run");
    }

    @Override
    public void run() {
        try {
            work();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public class ReenTranLockDemo {

    public static void main(String[] args) {
        Person person = new Person();
        new Thread(() -> {
            try {
                person.work();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                person.work();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
