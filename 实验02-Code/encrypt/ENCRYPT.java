package encrypt;
import encrypt.keygenerate.*;
import encrypt.permutation.*;
public class ENCRYPT {
    public static byte[] xor(byte[] a, byte[] b) {
        byte[] c = new byte[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = (byte) (a[i] ^ b[i]);
        return c;
    }
    public static byte[] encryptData(byte[]plainText,byte[]key64){
        byte[] cipherText=new byte[64];
        //产生子密钥
        byte[][] subkeys=GenerateKey.GeneateSubkeys(key64);
        //初始置换IP
        plainText=IP. InitalPermutation(plainText);
        byte[][] L = new byte[17][32];
        byte[][] R = new byte[17][32];
        //分成左半部分L0和右半部分R0
        for (int i = 0; i < 32; i++) {
            L[0][i] = plainText[i];
            R[0][i] = plainText[i + 32];
        }
        /*
        for(int i=0;i<L[0].length;i++){
            System.out.print(L[0][i]+" ");
        }
        System.out.println("");

        for(int i=0;i<R[0].length;i++){
            System.out.print(R[0][i]+" ");
        }
        System.out.println("");
        */

        //16次迭代加密
        for(int i=1;i<=16;i++){
            L[i]=R[i-1];
            R[i]=xor(L[i-1],F.function(R[i-1], subkeys[i-1]));
        }
        //以R16为左半部分，L16为右半部分合并
        for(int i=0;i<32;i++){
            cipherText[i]=R[16][i];
            cipherText[i+32]=L[16][i];
        }
        cipherText=IP_inverse.InitalPermutationInverse(cipherText);
        return cipherText;
    }
}
