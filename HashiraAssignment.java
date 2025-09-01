import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HashiraAssignment {

    // Function to solve polynomial coefficients from JSON
    public static void solveFromJson(String filePath) {
        try {
            // Read JSON file
            JSONObject json = new JSONObject(new JSONTokener(new FileReader(filePath)));

            int n = json.getJSONObject("keys").getInt("n"); // total roots
            int k = json.getJSONObject("keys").getInt("k"); // required roots (m+1)

            // Collect the first k roots
            List<BigInteger> roots = new ArrayList<>();
            for (int i = 1; i <= k; i++) {
                JSONObject obj = json.getJSONObject(String.valueOf(i));
                int base = Integer.parseInt(obj.getString("base"));
                String value = obj.getString("value");
                roots.add(new BigInteger(value, base)); // convert root to decimal
            }

            // Expand polynomial coefficients
            List<BigInteger> coeffs = new ArrayList<>();
            coeffs.add(BigInteger.ONE); // start with P(x) = 1

            for (BigInteger r : roots) {
                List<BigInteger> newCoeffs = new ArrayList<>(Collections.nCopies(coeffs.size() + 1, BigInteger.ZERO));
                for (int j = 0; j < coeffs.size(); j++) {
                    newCoeffs.set(j, newCoeffs.get(j).subtract(coeffs.get(j).multiply(r)));
                    newCoeffs.set(j + 1, newCoeffs.get(j + 1).add(coeffs.get(j)));
                }
                coeffs = newCoeffs;
            }

            // Print coefficients (highest degree first)
            System.out.println("Polynomial Coefficients for " + filePath + " (highest degree first):");
            for (int i = coeffs.size() - 1; i >= 0; i--) {
                System.out.print(coeffs.get(i) + " ");
            }
            System.out.println("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main function
    public static void main(String[] args) {
        solveFromJson("testcase1.json");
        solveFromJson("testcase2.json");
    }
}
