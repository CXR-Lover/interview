import java.math.BigDecimal;
import java.util.Random;

public class ArrayListTest {

    private static final int area = 1000;

    public static String getAmount(BigDecimal b1, BigDecimal b2, BigDecimal b3) {

        return null;
    }

    public static BigDecimal getRandomNumber(BigDecimal min, BigDecimal max) {

        Random r = new Random();
        return min.add(new BigDecimal(Math.random()).multiply(max));
    }

    public static void main(String[] args) {
        BigDecimal min = new BigDecimal(20);
        BigDecimal max = new BigDecimal(30);

        System.out.println(getRandomNumber(min, max));
    }
}
