package AES.decrypt;

import AES.SBoxes;
import AES.Xtime;

public class Inv_RoundFunction {
    public static byte[][] InvByteSub(byte[][]input){
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[i].length;j++){
                int low=(input[i][j])&(0b00001111);
                int high=((input[i][j])&(0b11110000))>>>4;
                input[i][j]=SBoxes.SBox_inverse[high][low];
            }
        }
        return input;
    }

    public static byte[][]InvShiftRow(byte[][]input){
        byte[][]temp=new byte[input.length][input[0].length];
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[i].length;j++){
                temp[i][j]=input[i][j];
            }
        }
        input[1][0]=temp[1][3];input[1][1]=temp[1][0];input[1][2]=temp[1][1];input[1][3]=temp[1][2];
        input[2][0]=temp[2][2];input[2][1]=temp[2][3];input[2][2]=temp[2][0];input[2][3]=temp[2][1];
        input[3][0]=temp[3][1];input[3][1]=temp[3][2];input[3][2]=temp[3][3];input[3][3]=temp[3][0];

        return input;
    }

    public static byte[][]InvMixColumn(byte[][]input){
        byte[] temp=new byte[4];
        byte[][] InvmixMatrix={
            {0x0e,0X0b,0x0d,0x09},
            {0x09,0x0e,0x0b,0x0d},
            {0x0d,0x09,0x0e,0x0b},
            {0x0b,0x0d,0x09,0x0e}
        };

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                temp[j]=input[j][i];
            }
            temp=MatrixTimesAES(InvmixMatrix, temp);
            for(int j=0;j<4;j++){
                input[j][i]=temp[j];
            }
        }
        return input;
    }

    public static byte[][] AddRoundKey(byte[][] input,int[] RoundKey,int i,boolean isMix){
        byte[][] op2=GenerateByteArray2(RoundKey, i);
        if(isMix)
            op2=InvMixColumn(op2);
        for(int j=0;j<4;j++){
            for(int k=0;k<4;k++){
                input[j][k]=(byte)(input[j][k]^op2[j][k]);
            }
        }
        return input;
    }

    public static byte[][] GenerateByteArray2(int[] RoundKey,int i){
        byte[][]res=new byte[4][4];
        byte[] temp=new byte[4];
        for(int j=0;j<4;j++){
            temp[0]=(byte)(RoundKey[i+j]>>>24);
            temp[1]=(byte)(RoundKey[i+j]>>>16);
            temp[2]=(byte)(RoundKey[i+j]>>>8);
            temp[3]=(byte)(RoundKey[i+j]);
            for(int k=0;k<4;k++){
                res[k][j]=temp[k];
            }
        }
        return res;
    }

    private static byte GFtime(byte input1,byte input2){
        byte ans=0;
        int high=(input2>>>4)&(0b00001111);
        int low=(input2)&(0b00001111);
        // 更新,使用直接表,测试加速
        switch (input1){
            case 0x1:
                ans=input2;
                break;
            case 0x2:
                ans=Xtime.xtimeTable[high][low];
                break;
            case 0x3:
                //ans=(byte)(input2^Xtime.xtimeTable[high][low]);
                ans=Xtime.x3timeTable[high][low];
                break;
            case 0x04:
                ans=GFtime((byte)0x02, GFtime((byte)0x02, input2));
                //(byte)(GFtime((byte)0x02, input2)^GFtime((byte)0x02, input2));
                break;
            case 0x08:
                //ans=GFtime((byte)0x02,GFtime((byte)0x02, GFtime((byte)0x02, input2)));
                ans=Xtime.x8timeTable[high][low];
                break;
            case 0x09:
                //ans=(byte)(GFtime((byte)0x08, input2)^input2);
                ans=Xtime.x9timeTable[high][low];
                //(byte)(GFtime((byte)0x02,GFtime((byte)0x02, GFtime((byte)0x02, input2)))^input2);
                break;
            case 0x0b:
                //ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x03, input2));
                ans=Xtime.x0BtimeTable[high][low];
                break;
            case 0x0d:
                //ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x04, input2)^input2);
                ans=Xtime.x0DtimeTable[high][low];
                break;
            case 0x0e:
                //ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x04, input2)^GFtime((byte)0x02, input2));
                ans=Xtime.x0EtimeTable[high][low];
                break;
            default:
                System.out.println("Error: can not calcullate in GF(2^8)");
                System.exit(-1);
        }
        return ans;
    }

    private static byte[] MatrixTimesAES(byte[][]matrix1,byte[]matrix2){
        byte[]ans=new byte[4];
        if(matrix1.length!=4||matrix1[0].length!=4||matrix2.length!=4){
            System.out.println("Error, can not do Matrix times");
            System.exit(-1);
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                ans[i]= (byte)(ans[i]^GFtime(matrix1[i][j], matrix2[j]));
            }
        }
        return ans;
    }

}
