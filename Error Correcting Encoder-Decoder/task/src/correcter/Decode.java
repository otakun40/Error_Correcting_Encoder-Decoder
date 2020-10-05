package correcter;

import java.io.*;

public class Decode {
    public static void decode() throws IOException {
        byte[] bytesReceived;
        try(InputStream inputStream = new FileInputStream("received.txt")) {
            bytesReceived = inputStream.readAllBytes();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytesReceived.length; i++) {
            byte byt = bytesReceived[i];

            String tempByte = Integer.toBinaryString(byt);
            int length = tempByte.length();
            String byteString = length < 8 ? "0".repeat(8 - length) + tempByte :
                    length > 8 ? tempByte.substring(length - 8) : tempByte;

            int x = Integer.parseInt(String.valueOf(byteString.charAt(0)));
            int y = Integer.parseInt(String.valueOf(byteString.charAt(1)));
            int a = Integer.parseInt(String.valueOf(byteString.charAt(2)));
            int z = Integer.parseInt(String.valueOf(byteString.charAt(3)));
            int b = Integer.parseInt(String.valueOf(byteString.charAt(4)));
            int c = Integer.parseInt(String.valueOf(byteString.charAt(5)));
            int d = Integer.parseInt(String.valueOf(byteString.charAt(6)));

            boolean p1 = (a ^ b ^ d) == x;
            boolean p2 = (a ^ c ^ d) == y;
            boolean p3 = (b ^ c ^ d) == z;

            int pos = (!p1 ? 1 : 0) + (!p2 ? 2 : 0) + (!p3 ? 4 : 0) - 1;

            if (pos == 2) {
                sb.append(a ^ 1).append(b).append(c).append(d);
            }   else if (pos == 4) {
                sb.append(a).append(b ^ 1).append(c).append(d);
            }   else if (pos == 5) {
                sb.append(a).append(b).append(c ^ 1).append(d);
            }   else if (pos == 6) {
                sb.append(a).append(b).append(c).append(d ^ 1);
            }   else {
                sb.append(a).append(b).append(c).append(d);
            }

            if (i % 2 == 1) {
                sb.append(" ");
            }
        }

        var a = sb.toString().split("\\s");

        byte[] decodedBytes = new byte[a.length];

        for (int i = 0; i < decodedBytes.length; i++) {
            decodedBytes[i] = (byte) Integer.parseInt(a[i], 2);
        }

        try (FileOutputStream fos = new FileOutputStream("decoded.txt")) {      // write decoded bytes to decoded.txt
            fos.write(decodedBytes);
        }
    }
}
