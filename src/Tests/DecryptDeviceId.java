package Tests;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.ByteArrayOutputStream;

/**
 * Created by wentaod on 8/11/15.
 */
public final class DecryptDeviceId {

    static class TanxDeviceIdDecryptException extends Exception {
        public TanxDeviceIdDecryptException(String msg) {
            super("Tanx Device Id Decryption Exception: " + msg);
        }
    }

    private static MessageDigest md5;

    private static final byte[] DIVICE_ID_ENCRYPTION_KEY = {
            (byte)0xf7, (byte)0xdb, (byte)0xeb, (byte)0x73, (byte)0x5b, (byte)0x7a, (byte)0x07, (byte)0xf1,
            (byte)0xcf, (byte)0xca, (byte)0x79, (byte)0xcc, (byte)0x1d, (byte)0xfe, (byte)0x4f, (byte)0xa4
    };

    public DecryptDeviceId() throws TanxDeviceIdDecryptException {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new TanxDeviceIdDecryptException("MD5 algorithm not available.");
        }
    }

    private static String decryptDeviceId(String id) throws TanxDeviceIdDecryptException {
        byte[] bytes = Base64.decodeBase64(id);

        int version = bytes[0];
        if (version != 1) {
            throw new TanxDeviceIdDecryptException("Version is not supported, version=" + version);
        }
        int len = bytes[1];
        byte[] key = md5.digest(DIVICE_ID_ENCRYPTION_KEY);

        // decoding
        int idxEncDevId = 2;
        int idxKey = 0;
        int idxKeyEnd = 16;
        StringBuffer sbDevId = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(version);
        outputStream.write(len);
        for (int i=0; i<len; ++i) {
            if (idxKey == idxKeyEnd) {
                idxKey = 0;
            }
            int xor = bytes[idxEncDevId++] ^ key[idxKey++];
            outputStream.write((byte) xor);
            sbDevId.append((char) xor);
        }

        // verification
        try {
            outputStream.write(DIVICE_ID_ENCRYPTION_KEY);
            byte[] verification = md5.digest(outputStream.toByteArray());
            for (int i=0; i<4; i++) {
                if(verification[i] != bytes[2+len+i]) {
                    throw new TanxDeviceIdDecryptException("Failed to verify CRC.");
                }
            }
        } catch (IOException e) {
            throw new TanxDeviceIdDecryptException("IOException when verifying CRC.");
        }

        return sbDevId.toString();
    }

    boolean validate(String input, String expected) throws TanxDeviceIdDecryptException {
        String output = decryptDeviceId(input);
        if(!expected.equals(output)) {
            System.out.println("Input: "+input+" and expect: "+expected+" but got: "+output);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            DecryptDeviceId dec = new DecryptDeviceId();
            dec.validate("AQ927DKCkp3HaJ+1n60VSBngmY2K", "493002407599521");
            dec.validate("ASRy5TGF4Z7HbYXG4NISVxy1c+wsi5CWwHXqxuSmFj8QwHDiQ/CVPeSb", "0007C145-FFF2-4119-9293-BFB26E8D27BB");
            dec.validate("AREDlCzw4IKwG4XE4rllPwW0c166xOg=", "AA-BB-CC-DD-EE-01");
        } catch (TanxDeviceIdDecryptException e) {
            System.out.println(e);
        }
    }
}
