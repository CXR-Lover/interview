package Sort;

import org.junit.Test;

public class BubbleSort {

    /**
     * ð������
     * ��1���ӵ�0������ʼ������0����С�ڵ�1����ʱ�����ཻ��
     * ��2���ӵ�1������ʼ������1����С�ڵ�2����ʱ�����ཻ��
     * ��3������i-1������ʼ������i-1����С�ڵ�i����ʱ�����ཻ�������Ե�һ��ѭ����ѭ��i-1��
     * (4) �ڶ���ѭ����
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
