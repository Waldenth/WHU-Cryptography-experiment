package encrypt;

public class E {
    /** 选择运算矩阵 */
    static int[] EMatrix = {
        32, 1,  2,  3,  4,  5, 
        4,  5,  6,  7,  8,  9,
        8,  9, 10, 11, 12,  13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32,  1
    };
    public static byte[] EWork(byte[] input,byte[] subkey){
        byte[] middleData=new byte[48];
        for(int i=0;i<48;i++)
            middleData[i]=(byte)(input[EMatrix[i]-1]^subkey[i]);
        return middleData;
    }
}
