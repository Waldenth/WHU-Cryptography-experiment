package AES.encrypt;

import AES.SBoxes;
import AES.Xtime;

public class RoundFunction {
    public static byte XOR(byte input1,byte input2){
        return (byte)(input1^input2);
    }
    
    public static byte[][] ByteSub(byte[][] input){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int low=(input[i][j])&(0b00001111);
                int high=((input[i][j])&(0b11110000))>>>4;
                //System.out.println("high="+high+" low="+low);
                input[i][j]=SBoxes.SBox[high][low];
            }
        }
        return input;
    }

    public static byte[][] ShiftRow(byte[][] input){
        byte[][]temp=new byte[input.length][input[0].length];
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[i].length;j++){
                temp[i][j]=input[i][j];
            }
        }
        
        input[1][0]=temp[1][1];input[1][1]=temp[1][2];input[1][2]=temp[1][3];input[1][3]=temp[1][0];
        input[2][0]=temp[2][2];input[2][1]=temp[2][3];input[2][2]=temp[2][0];input[2][3]=temp[2][1];
        input[3][0]=temp[3][3];input[3][1]=temp[3][0];input[3][2]=temp[3][1];input[3][3]=temp[3][2];
        
        return input;
    }

    public static byte[][] MixColumn(byte[][] input){
        byte []temp=new byte[4];
        
        byte[][] mixMatrix={
            {0x02,0x03,0x01,0x01},
            {0x01,0x02,0x03,0x01},
            {0x01,0x01,0x02,0x03},
            {0x03,0x01,0x01,0x02}
        };
        

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                temp[j]=input[j][i];
            }
            temp=MatrixTimesAES(mixMatrix, temp);
            for(int j=0;j<4;j++){
                input[j][i]=temp[j];
            }
        }
        return input;
    }

    public static byte[][] AddRoundKey(byte[][] input,int[] RoundKey,int i){
        byte[][] op2=GenerateByteArray2(RoundKey, i);
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


    public static byte GFtime(byte input1,byte input2){
        byte ans=0;
        int high=(input2>>>4)&(0b00001111);
        int low=(input2)&(0b00001111);
        switch (input1){
            case 0x1:
                ans=input2;
                break;
            case 0x2:
                ans=Xtime.xtimeTable[high][low];
                break;
            case 0x3:
                // 测试使用直接表
                //ans=(byte)(input2^Xtime.xtimeTable[high][low]);
                ans=Xtime.x3timeTable[high][low];
                break;
            default:
                System.out.println("Error: can not calcullate in GF(2^8)");
                System.exit(-1);
        }
        return ans;
    }

    public static byte[] MatrixTimesAES(byte[][]matrix1,byte[]matrix2){
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

    public static void TestRoundFunction(){
        byte[][] mixMatrix={
            {0x02,0x03,0x01,0x01},
            {0x01,0x02,0x03,0x01},
            {0x01,0x01,0x02,0x03},
            {0x03,0x01,0x01,0x02}
        };
        byte[] inputData={(byte)0x4d,(byte)0x90,(byte)0x4a,(byte)0xd8};
        byte[] outputata=MatrixTimesAES(mixMatrix, inputData);
        for(int i=0;i<4;i++){
            System.out.printf("0x%x\n",outputata[i]);
        }
    }

    public static void main(String[]args){
        TestRoundFunction();
    }

}
