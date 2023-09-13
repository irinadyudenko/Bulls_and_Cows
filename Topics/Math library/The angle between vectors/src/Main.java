import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double[] u = new double[2];
        double[] v = new double[2];
        u[0] = scanner.nextDouble();
        u[1] = scanner.nextDouble();
        v[0] = scanner.nextDouble();
        v[1] = scanner.nextDouble();
        double uv = u[0]*v[0] + u[1]*v[1];
        double magnU = Math.sqrt(u[0]*u[0]+u[1]*u[1]);
        double magnV = Math.sqrt(v[0]*v[0]+v[1]*v[1]);
        System.out.println(Math.toDegrees(Math.acos(uv/(magnU*magnV))));
    }
}