public class OfbMode {

    private final byte initVector;
    private final int key;
    public static final int OF = 4;

    public OfbMode(byte initVector, int key) {
        this.initVector = initVector;
        this.key = key;
    }

    public byte[] encryptOFB(byte[] pt) {
        SDES sdes = new SDES(key);
        byte ct[] = new byte[pt.length + 2];
        byte nextVector = initVector;
        for (int i = 0; i < pt.length; i++) {
            nextVector = sdes.encrypt(nextVector);
            ct[i] = (byte) (nextVector ^ pt[i]);
        }
        ct[pt.length] = OfbMode.OF;
        ct[pt.length + 1] = initVector;
        return ct;
    }

    public byte[] decryptOFB(byte[] ct) {
        SDES sdes = new SDES(key);
        byte[] pt = new byte[ct.length - 2];
        byte nextVector = initVector;
        for (int i = 0; i < pt.length; i++) {
            nextVector = sdes.encrypt(nextVector);
            pt[i] = (byte) (nextVector ^ ct[i]);
        }
        return pt;
    }

    public static byte[] fileDecryption(byte[] ct, int k) {
        byte iv = ct[ct.length - 1];
        OfbMode ofbMode = new OfbMode(iv, k);
        return ofbMode.decryptOFB(ct);
    }
}
