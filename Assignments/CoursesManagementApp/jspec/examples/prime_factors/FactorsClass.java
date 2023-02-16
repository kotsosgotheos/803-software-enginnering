import java.util.ArrayList;

public class FactorsClass {
    public ArrayList<Integer> factors_of(int remainder) {
        ArrayList<Integer> factors = new ArrayList<Integer>();

        int divisor = 2;
        
        while(remainder > 1) {
            while(remainder % divisor == 0) {
                factors.add(divisor);
                remainder = remainder / divisor;
            }
            divisor++;
        }

        return factors;
    }
}
