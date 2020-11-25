package encryptTools.des.encrypt;

import encryptTools.des.encrypt.keygenerate.GenerateKey;
import encryptTools.des.encrypt.permutation.IP;
import encryptTools.des.encrypt.permutation.IP_inverse;

public class DECRYPT_des {
	public static byte[] xor(byte[] a, byte[] b) {
        byte[] c = new byte[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = (byte) (a[i] ^ b[i]);
        return c;
    }
    public static byte[] decryptData(byte[] cipherText,byte[] key64){
        byte[]plainText=new byte[64];
        //产生子密钥
        byte[][] subkeys=GenerateKey.GeneateSubkeys(key64);
        //初始置换IP
        cipherText=IP.InitalPermutation(cipherText);
        byte[][]L=new byte[17][32];
        byte[][]R=new byte[17][32];
        //将密文分成左半部分R16和右半部分L16
        for(int i=0;i<32;i++){
            R[16][i]=cipherText[i];
            L[16][i]=cipherText[i+32];
        }
        //16次迭代解密
        for(int i=16;i>=1;i--){
            L[i-1]=xor(R[i],F.function(L[i], subkeys[i-1]));//subkey[0..15]
            R[i-1]=L[i];
        }
        //以L0为左半部分,R0为右半部合并
        for(int i=0;i<32;i++){
            plainText[i]=L[0][i];
            plainText[i+32]=R[0][i];
        }
        //逆初始置换
        plainText=IP_inverse.InitalPermutationInverse(plainText);
        return plainText;
    }
}
