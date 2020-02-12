import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Arrays;

public class AesEncrypt {

    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";

    public static byte[] encryptBase64EncodedWithManagedIV(String clearText, String key) throws Exception {
        byte[] textToEncrypt = clearText.getBytes();
        byte[] keyBytes = Base64.getDecoder().decode(key.getBytes());
        return encryptWithManagedIV(textToEncrypt, keyBytes);
    }

    public static byte[] encryptWithManagedIV(byte[] clearText, byte[] key) throws Exception{
        byte[] initialVector = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initialVector);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        return encrypt(clearText, key, initialVector);
    }

    public static byte[] encrypt(byte[] clearText, byte[] key, byte[] initialVector) throws Exception{
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpecy, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(clearText);

        byte[] ivAndCipherText = new byte[16 + cipherText.length];
        System.arraycopy(initialVector, 0, ivAndCipherText, 0, 16);
        System.arraycopy(cipherText, 0, ivAndCipherText, 16, cipherText.length);
        return ivAndCipherText;
    }

    public static void main(String args[]) throws Exception{
        String sClear = "The quick brown fox jumped over the lazy dog!";
        String sKey = "YWJjZGVmZ2hpamtsbW5vcA==";
        byte[] encryptedText = encryptBase64EncodedWithManagedIV(sClear, sKey);
        System.out.println("encryptedText:" + Base64.getEncoder().encodeToString(encryptedText));
    }
}