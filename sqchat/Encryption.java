package sqchat;

public class Encryption {

    private final static String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private byte key[];
    private int keylen;

    public Encryption() {
        keylen = 32;
        key = new byte[keylen];

        for (int i = 0; i < keylen; i++) {
            key[i] = (byte) 0xf;
        }
    }

    public Encryption(String base64key) {
        key = decode(base64key).getBytes();
        keylen = key.length;
    }

    public String decryptMsg(String msg) {
        return xor_crypt(decode(msg));
    }

    public String encryptMsg(String msg) {
        return encode(xor_crypt(msg));
    }

    private String xor_crypt(String s) {
        byte bytes[];
        int i, j;
        String ret;

        bytes = s.getBytes();
        i = 0;
        j = 0;

        while (i < bytes.length) {
            bytes[i] ^= key[j];

            i++;
            if (j < keylen) {
                j++;
            } else {
                j = 0;
            }
        }

        ret = new String(bytes);

        return ret;
    }

    private String encode(String s) {

        //I copied the code from 
        //https://en.wikibooks.org/wiki/Algorithm_Implementation/Miscellaneous/Base64#Java
        
        // the result/encoded string, the padding string, and the pad count
        String r = "", p = "";
        int c = s.length() % 3;

        // add a right zero pad to make this string a multiple of 3 characters
        if (c > 0) {
            for (; c < 3; c++) {
                p += "=";
                s += "\0";
            }
        }

        // increment over the length of the string, three characters at a time
        for (c = 0; c < s.length(); c += 3) {

            // we add newlines after every 76 output characters, according to
            // the MIME specs
            //if (c > 0 && (c / 3 * 4) % 76 == 0)
            //    r += "\r\n";

            // these three 8-bit (ASCII) characters become one 24-bit number
            int n = (s.charAt(c) << 16) + (s.charAt(c + 1) << 8)
                    + (s.charAt(c + 2));

            // this 24-bit number gets separated into four 6-bit numbers
            int n1 = (n >> 18) & 63, n2 = (n >> 12) & 63, n3 = (n >> 6) & 63, n4 = n & 63;

            // those four 6-bit numbers are used as indices into the base64
            // character list
            r += "" + base64chars.charAt(n1) + base64chars.charAt(n2)
                    + base64chars.charAt(n3) + base64chars.charAt(n4);
        }

        return r.substring(0, r.length() - p.length()) + p;
    }

    private String decode(String s) {
        
        //I copied the code from 
        //https://en.wikibooks.org/wiki/Algorithm_Implementation/Miscellaneous/Base64#Java

        // remove/ignore any characters not in the base64 characters list
        // or the pad character -- particularly newlines
        s = s.replaceAll("[^" + base64chars + "=]", "");

        // replace any incoming padding with a zero pad (the 'A' character is
        // zero)
        String p = (s.charAt(s.length() - 1) == '='
                ? (s.charAt(s.length() - 2) == '=' ? "AA" : "A") : "");
        String r = "";
        s = s.substring(0, s.length() - p.length()) + p;

        // increment over the length of this encrypted string, four characters
        // at a time
        for (int c = 0; c < s.length(); c += 4) {

            // each of these four characters represents a 6-bit index in the
            // base64 characters list which, when concatenated, will give the
            // 24-bit number for the original 3 characters
            int n = (base64chars.indexOf(s.charAt(c)) << 18)
                    + (base64chars.indexOf(s.charAt(c + 1)) << 12)
                    + (base64chars.indexOf(s.charAt(c + 2)) << 6)
                    + base64chars.indexOf(s.charAt(c + 3));

            // split the 24-bit number into the original three 8-bit (ASCII)
            // characters
            r += "" + (char) ((n >>> 16) & 0xFF) + (char) ((n >>> 8) & 0xFF)
                    + (char) (n & 0xFF);
        }

        // remove any zero pad that was added to make this a multiple of 24 bits
        return r.substring(0, r.length() - p.length());
    }
}
