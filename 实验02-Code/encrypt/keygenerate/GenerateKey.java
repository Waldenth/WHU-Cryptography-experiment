package encrypt.keygenerate;

public class GenerateKey{
    /** 置换选择1矩阵 */
    private static int[]replaceC={
        57,49,41,33,25,17,9,
        1,58,50,42,34,26,18,
        10,2,59,51,43,35,27,
        19,11,3,60,52,44,36
    };
    /** 置换选择1矩阵 */
    private static int[]replaceD={
        63,55,47,39,31,23,15,
        7,62,54,46,38,30,22,
        14,6,61,53,45,37,29,
        21,13,5,28,20,12,4
    };

    /** 置换选择2矩阵 */
    private static int[] replace2={
        14,17,11,24,1,5,
        3,28,15,6,21,10,
        23,19,12,4,26,8,
        16,7,27,20,13,2,
        41,52,31,
    };
   
    /** 循环左移位数表 */
    static int moveNums[]={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
    
    /** 循环左移方法 */
    public static byte[] SAL(byte[]data,int num){
        String s=new String(data);
        s=(s+s.substring(0,num)).substring(num);
        return s.getBytes();
    }

    /** 产生16个48位子密钥 */
    public static byte[][] GeneateSubkeys(byte[]key64){
        if(key64.length!=64){
            System.out.println("Error, key need 64 bits");
            System.exit(-1);
        }
        byte[][] subKeys = new byte[16][48];
        byte[] C=new byte[28];
        byte[] D=new byte[28];
        //置换选择1,产生C0,D0
        for(int i=0;i<28;i++){
            C[i]=key64[replaceC[i]-1];
            D[i]=key64[replaceD[i]-1];
        }
        //16次置换选择2,产生16个48位子密钥
        for(int i=0;i<16;i++){
            //左移
            C=SAL(C,moveNums[i]);
            D=SAL(D,moveNums[i]);
            for(int j=0;j<48;j++){
                
            }
        }

        
        return subKeys;
    }

    
}