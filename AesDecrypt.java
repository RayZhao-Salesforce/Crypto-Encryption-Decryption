import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Arrays;

public class AesDecrypt {

    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";

    public static byte[] decryptBase64EncodedWithManagedIV(String encryptedText, String key) throws Exception {
        byte[] cipherText = Base64.getDecoder().decode(encryptedText.getBytes());
        byte[] keyBytes = Base64.getDecoder().decode(key.getBytes());
        return decryptWithManagedIV(cipherText, keyBytes);
    }

    public static byte[] decryptWithManagedIV(byte[] cipherText, byte[] key) throws Exception{
        byte[] initialVector = Arrays.copyOfRange(cipherText,0,16);
        byte[] trimmedCipherText = Arrays.copyOfRange(cipherText,16,cipherText.length); 
        return decrypt(trimmedCipherText, key, initialVector);
    }

    public static byte[] decrypt(byte[] cipherText, byte[] key, byte[] initialVector) throws Exception{
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }

    public static void main(String args[]) throws Exception{
        String sCipher = "VJCwhbkG2wNkpeERX+xHzfMJfMMEjriVnuXy9Qt+r6XcTSNEVXgmxiDVQmBRXTZbMEiBcWjjyxPgHMTJ5YiDGA==";
        String sKey = "YWJjZGVmZ2hpamtsbW5vcA=="; 
        byte[] clearText = decryptBase64EncodedWithManagedIV(sCipher, sKey);
        System.out.println("ClearText:" + new String(clearText, "UTF-8"));
    }
}