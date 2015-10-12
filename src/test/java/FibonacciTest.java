import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by ulises on 12/10/15.
 */
public class FibonacciTest {

    private Fibonacci fibonacci;


    @Before
    public void setUp() throws Exception {
        fibonacci = new Fibonacci();
    }


    private double rootSquareOf5 = Math.sqrt(5);

    @Test
    public void testFibonacci() throws Exception {

        for (int i = 0; i < 100; i++) {
            BigInteger nFiboNumber = fibonacci.calculateFibonacciNumber(i);
            Assert.assertTrue(fibonacci.isFibonacci(nFiboNumber));
        }

    }
}
