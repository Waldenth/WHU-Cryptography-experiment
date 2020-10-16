package AES;

import java.io.File;

import AES.decrypt.DECRYPT;
import AES.encrypt.ENCRYPT;

public class TestAES {
    public static void main(String []args){
        SBoxProblem.TestBox();
        ENCRYPT.TestEncrypt();
        DECRYPT.TestDecrypt();
        String key="abcdabcdabcdabcd";
        byte[]tmp=key.getBytes();
        System.out.println(tmp.length);
        FileAPI.EncryptFiles("key.txt", true, "plainText.txt","MycipherText.txt");
        FileAPI.DecryptFiles("key.txt", true, "MycipherText.txt","MydecryptedText.txt");
        FileAPI.EncryptFiles(key, false, "plainText.txt");
        FileAPI.DecryptFiles(key, false, "cipherText.txt");
        /** Test image file ed crypt*/
        FileAPI.EncryptFiles("key.txt", true, "Histoie.jpg", "CipherHistoie.jpg");
        FileAPI.DecryptFiles("key.txt", true, "CipherHistoie.jpg", "DecryptedHistoie.jpg");
    }
}
