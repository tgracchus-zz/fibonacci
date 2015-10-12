import java.math.BigInteger;

/**
 * Fibonacci numbers base on raise to the power the identity matrix
 * Created by ulises on 12/10/15.
 */
public class Fibonacci {

    private final static BigInteger identity[][] =
            {{BigInteger.valueOf(1), BigInteger.valueOf(1)},
                    {BigInteger.valueOf(1), BigInteger.valueOf(0)}};

    public BigInteger calculateFibonacciNumber(long n) {
        if (n == 0) {
            return BigInteger.valueOf(0);
        }

        BigInteger resultMatrix[][] =
                {{BigInteger.valueOf(1), BigInteger.valueOf(1)},
                        {BigInteger.valueOf(1), BigInteger.valueOf(0)}};

        raiseToThePowerOf(resultMatrix, n - 1);
        return resultMatrix[0][0];
    }

    public boolean isFibonacci(BigInteger fiboCandidate) {

        return isPerfectSquare(
                fiboCandidate.multiply(fiboCandidate)
                        .multiply(BigInteger.valueOf(5))
                        .add(BigInteger.valueOf(4)))

                || isPerfectSquare(
                fiboCandidate.multiply(fiboCandidate)
                        .multiply(BigInteger.valueOf(5))
                        .subtract(BigInteger.valueOf(4)));
    }


    private boolean isPerfectSquare(BigInteger x) {
        BigInteger s = sqrt(x);
        return (s.multiply(s).equals(x));
    }

    private BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while (b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if (mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }

    private void raiseToThePowerOf(BigInteger[][] result, long n) {
        for (int i = 2; i <= n; i++) {
            strassenMatrixMultiplication(result, identity);
        }
    }

    /**
     * Using Strassen multiplication algorithm
     */
    private void strassenMatrixMultiplication(BigInteger A[][], BigInteger B[][]) {
        BigInteger s1 = B[0][1].subtract(B[1][1]);
        BigInteger s2 = A[0][0].add(A[0][1]);
        BigInteger s3 = A[1][0].add(A[1][1]);
        BigInteger s4 = B[1][0].subtract(B[0][0]);
        BigInteger s5 = A[0][0].add(A[1][1]);
        BigInteger s6 = B[0][0].add(B[1][1]);
        BigInteger s7 = A[0][1].subtract(A[1][1]);
        BigInteger s8 = B[1][0].add(B[1][1]);
        BigInteger s9 = A[0][0].subtract(A[1][0]);
        BigInteger s10 = B[0][0].add(B[0][1]);

        BigInteger p1 = A[0][0].multiply(s1);
        BigInteger p2 = s2.multiply(B[1][1]);
        BigInteger p3 = s3.multiply(B[1][1]);
        BigInteger p4 = A[1][1].multiply(s4);
        BigInteger p5 = s5.multiply(s6);
        BigInteger p6 = s7.multiply(s8);
        BigInteger p7 = s9.multiply(s10);

        A[0][0] = p5.add(p4).subtract(p2).add(p6);
        A[0][1] = p1.add(p2);
        A[1][0] = p3.add(p4);
        A[1][1] = p5.add(p1).subtract(p3).subtract(p7);

    }


}
