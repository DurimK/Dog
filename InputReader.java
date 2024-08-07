//Jonas Levin jole3800

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    //keep track of all instances.
    private static ArrayList<InputStream> streams = new ArrayList<>();
    private InputStream inputStream;
    private final Scanner scanner;
    /*
     * Constructor that calls itself with the system input.
     */

    public InputReader() {
        this(System.in);
    }
    /*
     * Constructor that uses inputstream to get the input from the user.
     * @param inputStream The inputstream that decides the prompt line.
     */
    public InputReader(InputStream inputStream) {
        if (streams.contains(inputStream)) {
            throw new IllegalStateException("Error: InputStream is already in use by another instance.");
        }
        this.scanner = new Scanner(inputStream);
        this.inputStream = inputStream;
        streams.add(inputStream);
    }

    public String readLine(String prompt) {
        System.out.print(prompt + "?> ");
        return scanner.nextLine();
    }

    public int readInteger(String prompt) {
        System.out.print(prompt + "?> ");
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public double readDecimal(String prompt) {
        System.out.print(prompt + "?> ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public void close() {
        scanner.close();
        streams.remove(this.inputStream);
    }
}