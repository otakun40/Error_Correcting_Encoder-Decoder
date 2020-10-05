package correcter;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String input = scanner.next();
        if ("encode".equals(input)) {
            Encode.encode();
        } else if ("send".equals(input)) {
            Send.sendWithErrors();
        } else if ("decode".equals(input)) {
            Decode.decode();
        }
    }
}