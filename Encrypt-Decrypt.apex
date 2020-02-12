	//Testing Encryption
    Blob key = EncodingUtil.base64Decode('YWJjZGVmZ2hpamtsbW5vcA==');
    System.debug(loggingLevel.INFO, 'Encrypted ==>' + key.size());
    Blob data = Blob.valueOf('ginzele1@gmail.com');
    Blob encrypted = Crypto.encryptWithManagedIV('AES128', key, data);
	String strEncrypted = EncodingUtil.base64Encode(encrypted);
    System.debug(loggingLevel.INFO, 'Encrypted ==>' + strEncrypted);

	//Testing Decryption
	String strEncrypted = 'SiuoCI7SYdA4YvxFWmOU6NNtfNjVFTCc7qS0Igx3eA0+1+Yqm48WOzzxG9XxFv2J';
	Blob key = EncodingUtil.base64Decode('YWJjZGVmZ2hpamtsbW5vcA==');
	Blob blbEncrypted = EncodingUtil.base64Decode(strEncrypted);
	Blob decrypted = Crypto.decryptWithManagedIV('AES128', key, blbEncrypted);	
	String dectyptecString = EncodingUtil.base64Encode(decrypted);
	System.debug(LoggingLevel.INFO, 'Decrypted ==>' + decryptedString);  
