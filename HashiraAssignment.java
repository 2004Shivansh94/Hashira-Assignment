import java.math.BigInteger;
import java.util.*;

public class HashiraAssignment {

    public static void solveTestcase(String[] values, int[] bases, int k) {
        List<BigInteger> roots = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            roots.add(new BigInteger(values[i], bases[i]));
        }

        List<BigInteger> coeffs = new ArrayList<>();
        coeffs.add(BigInteger.ONE);

        for (BigInteger r : roots) {
            List<BigInteger> newCoeffs = new ArrayList<>(Collections.nCopies(coeffs.size() + 1, BigInteger.ZERO));
            for (int j = 0; j < coeffs.size(); j++) {
                newCoeffs.set(j, newCoeffs.get(j).subtract(coeffs.get(j).multiply(r)));
                newCoeffs.set(j + 1, newCoeffs.get(j + 1).add(coeffs.get(j)));
            }
            coeffs = newCoeffs;
        }

        System.out.println("Polynomial Coefficients (highest degree first):");
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            System.out.print(coeffs.get(i) + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        // ===== Testcase 1 =====
        String[] values1 = {"4", "111", "12"};
        int[] bases1 = {10, 2, 10};
        int k1 = 3;
        System.out.println("Output for Testcase 1:");
        solveTestcase(values1, bases1, k1);

        // ===== Testcase 2 =====
        String[] values2 = {
            "13444211440455345511", 
            "aed7015a346d635", 
            "6aeeb69631c227c", 
            "e1b5e05623d881f", 
            "316034514573652620673", 
            "2122212201122002221120200210011020220200", 
            "20120221122211000100210021102001201112121"
        };
        int[] bases2 = {6, 15, 15, 16, 8, 3, 3};
        int k2 = 7;
        System.out.println("Output for Testcase 2:");
        solveTestcase(values2, bases2, k2);
    }
}
