package encrypt;

public class P {
    static int[] PMatrix={
        16,  7, 20, 21,
        29, 12, 28, 17,
         1, 15, 23, 26,
         5, 18, 31, 10,
         2,  8, 24, 14,
        32, 27,  3,  9,
        19, 13, 30,  6,
        22, 11,  4, 25
    };
    
    public static byte[] PWork(byte[] input){
        byte[] result=new byte[32];
        for(int i=0;i<32;i++){
            result[i]=input[PMatrix[i]-1];
        }
        return result;
    }
}
