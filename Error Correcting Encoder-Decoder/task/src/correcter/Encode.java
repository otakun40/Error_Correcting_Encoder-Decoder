package correcter;

import java.io.*;

public class Encode {

    public static void encode() throws IOException {
        byte[] source;
        try(InputStream inputStream = new FileInputStream("send.txt")) {
            source = inputStream.readAllBytes();
        }

        StringBuilder sb = new StringBuilder();

        for (byte byt : source) {

            int length = Integer.toBinaryString(byt).length();
            String byteString = length < 8 ? "0".repeat(8 - length) + Integer.toBinaryString(byt):
                    length > 8 ? Integer.toBinaryString(byt).substring(length - 8): Integer.toBinaryString(byt);

            for (int i = 0; i < 8; i += 4) {

                int a = Integer.parseInt(String.valueOf(byteString.charAt(i)));
                int b = Integer.parseInt(String.valueOf(byteString.charAt(i + 1)));
                int c = Integer.parseInt(String.valueOf(byteString.charAt(i + 2)));
                int d = Integer.parseInt(String.valueOf(byteString.charAt(i + 3)));

                int x = a ^ b ^ d;
                int y = a ^ c ^ d;
                int z = b ^ c ^ d;

                sb.append(x).append(y).append(a).append(z).append(b).append(c).append(d).append(0).append(" ");
            }
        }

        String[] byteStrings = sb.toString().split("\\s"); // split string by bytes
        byte[] bytesResult = new byte[byteStrings.length];

        for (int i = 0; i < byteStrings.length; i++) {
            bytesResult[i] = (byte) Integer.parseInt(byteStrings[i], 2);
        }

        try (FileOutputStream fos = new FileOutputStream("encoded.txt")) { // write encoded bytes
            fos.write(bytesResult);
        }
    }
}
