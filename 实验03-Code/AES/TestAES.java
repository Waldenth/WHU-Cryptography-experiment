package AES;

import AES.decrypt.DECRYPT;
import AES.encrypt.ENCRYPT;

public class TestAES {
    public static void main(String []args){
        SBoxProblem.TestBox();
        ENCRYPT.TestEncrypt();
        DECRYPT.TestDecrypt();
    }
}
