import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BloomFilter {

    private static final int NUM_SLOTS = 1 << 23;
    private static final int NUM_HASH = 1 << 3;

    private BigInteger bitmap = new BigInteger("0");

    public boolean check(String msg) {
        for (int i = 0; i < NUM_HASH; i++) {
            int hashcode = getHash(msg, i);
            if (!bitmap.testBit(hashcode)) {
                return false;
            }
        }
        return true;
    }

    public void addElement(String msg) {
        for (int i = 0; i < NUM_HASH; i++) {
            int hashcode = getHash(msg, i);
            if (!bitmap.testBit(hashcode)) {
                bitmap = bitmap.or(new BigInteger("1").shiftLeft(hashcode));
            }
        }
    }

    private int getHash(String msg, int n) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            msg = msg + String.valueOf(n);
            byte[] bytes = msg.getBytes();
            md5.update(bytes);
            BigInteger bi = new BigInteger(md5.digest());
            return Math.abs(bi.intValue()) % NUM_SLOTS;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter();
        ArrayList<String> contents = new ArrayList<String>();
        contents.add("element1");
        contents.add("element2");
        contents.add("element3");
        contents.add("element4");
        contents.add("element4");

        for (String content : contents) {
            bf.addElement(content);
        }

        System.out.println(bf.check("element4"));
        System.out.println(bf.check("element"));
    }
}
