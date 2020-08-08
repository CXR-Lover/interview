package Sort;

import org.junit.Test;

public class BubbleSort {

    /**
     * 冒泡排序
     * （1）从第0个数开始，当第0个数小于第1个数时，互相交换
     * （2）从第1个数开始，当第1个数小于第2个数时，互相交换
     * （3）当第i-1个数开始，当第i-1个数小于第i个数时，互相交换，所以第一次循环共循环i-1次
     * (4) 第二次循环，
     */
    @Test
    public void BubbleSortTest() {
        int array[] = {1, 6, 3, 4, 8, 3, 9, 11};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            for (int b : array) {
                System.out.print(b);
            }
            System.out.println("\n");
        }
        System.out.println(array);
    }
}
