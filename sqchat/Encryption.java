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
        key = base64decode(base64key);
        keylen = key.length;
    }

    public String decryptMsg(String msg) {
        System.out.println("decryptl: " + msg.length());
        return xor_crypt(base64decodeMsg(msg));
    }

    public String encryptMsg(String msg) {
        System.out.println("encryptl: " + msg.length());
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
            if(j >= key.length) {
                System.out.println(j + " ... " + key.length + " --- " + keylen);
                j = 0;
            }
            bytes[i] ^= key[j];

            i++;
            j++;
            if (j >= keylen) {
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
}
