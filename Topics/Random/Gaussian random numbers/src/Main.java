import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();
        int seed;
        boolean success = true;
        for (int i = k; ; i++) {
            Random rand = new Random(i);
            for (int j = 0; j < n; j++) {
                double curNum = rand.nextGaussian();
                if (curNum > m) {
                    success = false;
                    break;
                }
            }
            if (success) {
                seed = i;
                break;
            }
            success = true;
        }
        System.out.println(seed);
        // write your code here
    }
}