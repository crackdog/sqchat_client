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
        
        keylen = 32;
        key = new byte[keylen];

        for (int i = 0; i < keylen; i++) {
            key[i] = (byte) 0xf;
        }
    }

    public String decryptMsg(String msg) {
        return xor_crypt(decode(msg));
    }

    public String encryptMsg(String msg) {
        return base64encode(xor_crypt(msg).getBytes());
    }
    
    public String base64decodeMsg(String msg) {
        return new String(base64decode(msg));
    }
    
    public String base64encodeMsg(String msg) {
        return base64encode(msg.getBytes());
    }

    private byte[] xor_crypt(byte[] bytes) {
        int i, j;

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

        return bytes;
    }

    private String xor_crypt(String s) {
        return new String(xor_crypt(s.getBytes()));
    }

    private String base64encode(byte[] rawdata) {
        String base64str, padStr;
        int padCount, datalength, i, tmp;
        byte[] data;

        base64str = "";
        padStr = "";

        datalength = rawdata.length;

        padCount = rawdata.length % 3;
        if (padCount > 0) {
            while (padCount < 3) {
                padStr += '=';
                datalength++;
                padCount++;
            }
        }

        data = new byte[datalength];

        for (i = 0; i < datalength; i++) {
            if (i < rawdata.length) {
                data[i] = rawdata[i];
            } else {
                data[i] = '\0';
            }
        }

        for (i = 0; i < datalength; i += 3) {
            tmp = 0;
            tmp += (byte) data[i] << 16;
            tmp += (byte) data[i + 1] << 8;
            tmp += (byte) data[i + 2];

            base64str += base64chars.charAt((tmp >> 18) & 63);
            base64str += base64chars.charAt((tmp >> 12) & 63);
            base64str += base64chars.charAt((tmp >> 6) & 63);
            base64str += base64chars.charAt(tmp & 63);
        }

        base64str = base64str.substring(0, base64str.length() - padStr.length()) + padStr;

        return base64str;
    }

    private byte[] base64decode(String encodedDataString) {
        int decodedDataLength, encodedDataLength;
        byte[] decodedData, encodedData;
        int padCount, i, j, tmp;

        //remove all characters that are not in the base64 character list
        encodedDataString = encodedDataString.replaceAll("[^" + base64chars + "=]", "");

        //saving encodedDataString as byte Array
        encodedDataLength = encodedDataString.length();
        encodedData = encodedDataString.getBytes();

        //replace padding chars with a pad that decodes to zero ("A")
        if (encodedData[encodedDataLength - 1] == '=') {
            encodedData[encodedDataLength - 1] = 'A';

            if (encodedData[encodedDataLength - 2] == '=') {
                encodedData[encodedDataLength - 2] = 'A';
            }
        }

        decodedDataLength = encodedDataLength / 4 * 3 + 1;
        decodedData = new byte[decodedDataLength];

        for (i = 0, j = 0; i < encodedDataLength && j < decodedDataLength; i += 4, j += 3) {
            //read 4 numbers and store in 24 bit number
            tmp = base64chars.indexOf(encodedData[i]) << 18;
            tmp += base64chars.indexOf(encodedData[i + 1]) << 12;
            tmp += base64chars.indexOf(encodedData[i + 2]) << 6;
            tmp += base64chars.indexOf(encodedData[i + 3]);

            //save 24 bit number in 3 bytes...
            decodedData[j] = (byte) ((tmp >> 16) & 0xff);
            decodedData[j + 1] = (byte) ((tmp >> 8) & 0xff);
            decodedData[j + 2] = (byte) (tmp & 0xff);
        }

        return decodedData;
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
