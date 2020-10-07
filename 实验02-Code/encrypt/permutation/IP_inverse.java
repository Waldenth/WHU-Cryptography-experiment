package encrypt.permutation;

public class IP_inverse {
    /** 逆初始置换IP矩阵 */
    static int[] IPiv={
        40,  8, 48, 16, 56, 24, 64, 32, 
        39,  7, 47, 15, 55, 23, 63, 31, 
        38,  6, 46, 14, 54, 22, 62, 30, 
        37,  5, 45, 13, 53, 21, 61, 29, 
        36,  4, 44, 12, 52, 20, 60, 28, 
        35,  3, 43, 11, 51, 19, 59, 27, 
        34,  2, 42, 10, 50, 18, 58, 26, 
        33,  1, 41,  9, 49, 17, 57, 25
    };
    /**
     * 逆初始置换IP
     * @param text byte array
     * @return byte arrsy
    */
    public static byte[] InitalPermutationInverse(byte[]text){
        byte[] newText=new byte[64];
        for(int i=0;i<64;i++)
            newText[i]=text[IPiv[i]-1];
        return newText;
    }
}
