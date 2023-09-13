import java.util.Scanner;

class EvenUpperCase {

    public static String upperEvenLetters(String message) {
        // write your code here
        StringBuilder resultString = new StringBuilder(message);
        for (int i = 0; i < message.length(); i+=2) {
            resultString.replace(i, i+1, String.valueOf(message.charAt(i)).toUpperCase());
        }
        return resultString.toString();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();

        System.out.println(upperEvenLetters(message));
    }
}