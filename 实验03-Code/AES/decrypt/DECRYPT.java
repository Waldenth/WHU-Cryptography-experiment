package AES.decrypt;

import AES.keygenerate.KeyExpansion;

public class DECRYPT {
    public static byte[]decryptData(byte[] cipherText,byte[]key){
        int[] RoundKey=KeyExpansion.keyExpansion(key);
        byte[][]tmpData=ConvertToMatrix(cipherText);

        //初始轮密钥加
        tmpData=Inv_RoundFunction.AddRoundKey(tmpData, RoundKey, 40,false);

        /** 前9轮变换 */
        for(int i=8;i>=0;i--){
            tmpData=Inv_RoundFunction.InvByteSub(tmpData);
            
            tmpData=Inv_RoundFunction.InvShiftRow(tmpData);

            tmpData=Inv_RoundFunction.InvMixColumn(tmpData);

            tmpData=Inv_RoundFunction.AddRoundKey(tmpData, RoundKey, i*4+4, true);
        }

        /** 最后一轮变换 */
        tmpData=Inv_RoundFunction.InvByteSub(tmpData);
        tmpData=Inv_RoundFunction.InvShiftRow(tmpData);
        tmpData=Inv_RoundFunction.AddRoundKey(tmpData, RoundKey, 0, false);

        cipherText=ConvertToArray(tmpData);

        return cipherText;
    }

    



    private static byte[][] ConvertToMatrix(byte[]input){
        if(input.length!=16){
            System.out.println("Error: can not convert to 4x4 matrix");
            System.exit(-1);
        }
        byte[][]output=new byte[4][4];
        //AES要求按列写入
        int k=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                output[i][j]=input[k];
                k++;
            }
        }
        return output;
    }

    private static byte[] ConvertToArray(byte[][]input){
        if(input.length!=4||input[0].length!=4){
            System.out.println("Error:can not convert to Array which's length is 16");
            System.exit(-1);
        }
        byte[]output=new byte[16];
        //按列取出
        int k=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                output[k]=input[i][j];
                k++;
            }
        }
        return output;
    }

    public static void TestDecrypt(){
        byte[] cipherData={
            0x6c,(byte)0xdd,0x59,0x6b,(byte)0x8f,0x56,0x42,(byte)0xcb,(byte)0xd2,0x3b,0x47,(byte)0x98,0x1a,0x65,0x42,0x2a
        };
        byte[] key={
            0x00,0x01,0x20,0x01,0x71,0x01,(byte)0x98,(byte)0xae,(byte)0xda,0x79,0x17,0x14,0x60,0x15,0x35,(byte)0x94
        };
        byte[]messageData=decryptData(cipherData, key);
        for(int i=0;i<cipherData.length;i++){
            System.out.printf("0x%x ", messageData[i]);
        }
        System.out.println("");
    }

    public static void main(String[]args){
        TestDecrypt();
    }
   
}
