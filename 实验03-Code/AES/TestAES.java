package AES;

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
        //FileAPI.EncryptFiles("key.txt", true, "plainText.txt","MycipherText.txt");
        //FileAPI.DecryptFiles("key.txt", true, "MycipherText.txt","MydecryptedText.txt");
        //FileAPI.EncryptFiles(key, false, "plainText.txt");
        //FileAPI.DecryptFiles(key, false, "cipherText.txt");
        /** Test image file ed crypt*/
        FileAPI.EncryptFiles("key.txt", true, "Histoie.jpg", "CipherHistoie.jpg");
        FileAPI.DecryptFiles("key.txt", true, "CipherHistoie.jpg", "DecryptedHistoie.jpg");
        //String filepath="F:/工作区/编译原理 Tiny/源代码/yacc/Detiny.y";
        //String outpath="F:/工作区/编译原理 Tiny/源代码/yacc/Entiny.y";
        //FileAPI.DecryptFiles("key.txt", true, outpath, filepath);
    }
    public static void test(){
        FileAPI.EncryptFiles("key.txt", true, "Histoie.jpg", "CipherHistoie.jpg");
        FileAPI.DecryptFiles("key.txt", true, "CipherHistoie.jpg", "DecryptedHistoie.jpg");
    }
}
