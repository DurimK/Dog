import java.util.Scanner;

public class HR4_2_4StaticScannerDemo {

    private static Scanner input = new Scanner(System.in);

    private static void multiplyByTwo() {
        System.out.print("Skriv ett heltal: ");
        int i = input.nextInt();
        System.out.printf("%d*2=%d%n", i, i * 2);
    }

    private static void multiplyByThree() {
        System.out.print("Skriv ett heltal: ");
        int i = input.nextInt();
        System.out.printf("%d*3=%d%n", i, i * 3);
    }

    private static void multiplyNumbers() {
        multiplyByTwo();
        multiplyByThree();
    }

    public static void main(String[] args) {
        multiplyNumbers();
    }

}