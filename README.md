# Crypto-Encryption-Decryption
Salesforce Crypto Class uses AES/CBC/PKCS5Padding Encryption Standard.  This sample is what I found and modified Java code to work with Salesforce Apex Crypto Class.

With different encoding/decoding, encryption/decryption standards, I struggled with troubleshooting other developers' codes that don't seem to work.  For that reason, once I found what's going on, I want to provide what I learned to help other developers in need.

Salesforce crypto.decryptWithManagedIV() expects the IV to be the first 16-byte of the cipher text.  The AesEncrypt.java gets a random IV and add it in front of the cipher text.  The cipher text can be decrypted by the mentioned crypto class method.  Vice versa, the AesDecrypt.java works with the crypto.encryptWithManagedIV() the same way.

I also included some code segments of the Apex sample code that can execute inside the Anonymous Execution block.  These Apex code segments work well with the java AES classes that I provided here.

This should help some developers who are looking for the standard way to fulfill the security requirements.
