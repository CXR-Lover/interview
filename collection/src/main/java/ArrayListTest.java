import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    @Test
    public void Add() {
        List list = new ArrayList();
        list.add("123");
        list.add("456");
        list.add(1234);
        list.add(4567);
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer[]{3});

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
