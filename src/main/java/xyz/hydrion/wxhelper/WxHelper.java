package xyz.hydrion.wxhelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.io.PrintWriter;


public class WxHelper {

    public static void respond(PrintWriter out,
                               String token, String signature,
                               String timestamp, String nonce,
                               String echostr){
        if (WxHelper.checkSignature(token, signature, timestamp, nonce)) {
            out.print(echostr);
            out.close();
        }
    }

    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        String tmp = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7',
                        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] tempArr = new char[2];
                tempArr[0] = Digit[(b >>> 4) & 0X0F];
                tempArr[1] = Digit[b & 0X0F];
                builder.append(tempArr);
            }
            tmp = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tmp != null && tmp.equals(signature.toUpperCase());
    }
}
