package AES.encrypt;

import AES.encrypt.keygenerate.KeyExpansion;

public class ENCRYPT {
    public static byte[]encryptData(byte[] messageData,byte[] key){
        int[] RoundKey=KeyExpansion.keyExpansion(key);
        byte[][]tmpData=ConvertToMatrix(messageData);

        //byte[] debug=new byte[16];

        tmpData=RoundFunction.AddRoundKey(tmpData, RoundKey, 0);//初始轮密钥加
        /*
        debug=ConvertToArray(tmpData);
        debugPrint(debug);
        for(int i=0;i<RoundKey.length;i++)
            System.out.printf("%x ",RoundKey[i]);
        System.out.println("");
        */
        
        /** 前9轮变换 */
        for(int i=0;i<9;i++){
            tmpData=RoundFunction.ByteSub(tmpData);//S盒变换
            //debug=ConvertToArray(tmpData);
            //debugPrint(debug);
            tmpData=RoundFunction.ShiftRow(tmpData);//行移位变换
            //debug=ConvertToArray(tmpData);
            //debugPrint(debug);
            tmpData=RoundFunction.MixColumn(tmpData);//列混合变换
            //debug=ConvertToArray(tmpData);
            //debugPrint(debug);
            tmpData=RoundFunction.AddRoundKey(tmpData, RoundKey, i*4+4);
            //debug=ConvertToArray(tmpData);
            //debugPrint(debug);
        }

        /**  最后一轮变换*/
        tmpData=RoundFunction.ByteSub(tmpData);
        tmpData=RoundFunction.ShiftRow(tmpData);
        tmpData=RoundFunction.AddRoundKey(tmpData, RoundKey, 40);

        messageData=ConvertToArray(tmpData);

        return messageData;
    }

    public static byte[][] ConvertToMatrix(byte[]input){
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

    public static byte[] ConvertToArray(byte[][]input){
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
    public static void debugPrint(byte[]data){
        for(int i=0;i<data.length;i++){
            System.out.printf(" %x ", data[i]);
        }
        System.out.println("");
    }


    public static void main(String[]args){
        byte[] messageData={
            0x00,0x01,0x00,0x01,0x01,(byte)0xa1,(byte)0x98,(byte)0xaf,(byte)0xda,0x78,0x17,0x34,(byte)0x86,0x15,0x35,0x66
        };
        byte[] key={
            0x00,0x01,0x20,0x01,0x71,0x01,(byte)0x98,(byte)0xae,(byte)0xda,0x79,0x17,0x14,0x60,0x15,0x35,(byte)0x94
        };

        byte[] cipherText=encryptData(messageData, key);

        for(int i=0;i<cipherText.length;i++){
            System.out.printf("%x ", cipherText[i]);
        }
        System.out.println("");
    }

}
