package AES.encrypt.keygenerate;

import java.util.Arrays;

import AES.SBoxes;
import AES.Xtime;

public class KeyExpansion {
    static int Rcon[]={
        0xFFFFFFFF,0x01000000,0x02000000,0x04000000,0x08000000,0x10000000,
        0x20000000,0x40000000,0x80000000,0x1B000000,0x36000000
    };
    
    
    public static int[] keyExpansion(byte[]key){
        int[] W=new int[44];//4*(10+1)
        if(key.length!=16){
            System.out.println("Error:This System now only support 128bit key");
            System.exit(-1);
        }
        byte [] tmp;
        

        for(int i=0;i<4;i++){
            tmp=Arrays.copyOfRange(key,i*4,i*4+4);
            W[i]=ByteArrayToInt(tmp);
        }
        for(int i=4;i<44;i++){
            int temp=W[i-1];
            if(i%4==0){
                temp=SubByte(Rotl(temp)) ^ Rcon[i/4];
            }
            W[i]=W[i-4]^temp;
        }
        return W;
    }
    public static int SubByte(int input){
        byte[] temp=new byte[4];
        temp[0]=(byte)(input>>>24);
        temp[1]=(byte)(input>>>16);
        temp[2]=(byte)(input>>>8);
        temp[3]=(byte)(input);
        
        for(int i=0;i<4;i++){
            int low=temp[i]&(0b00001111);
            int high=((temp[i])&(0b11110000))>>>4;
            //System.out.printf("high=%d,low=%d\n",high,low);
            temp[i]=SBoxes.SBox[high][low];
        }
        return ByteArrayToInt(temp);
    }
    public static int Rotl(int input){//ABCD
        byte[] temp=new byte[4];
        temp[3]=(byte)(input>>>24);//A
        temp[0]=(byte)(input>>>16);//B
        temp[1]=(byte)(input>>>8);//C
        temp[2]=(byte)(input);    //D
        return ByteArrayToInt(temp);
    }

    /** 废弃 */
    public static int Rcon(int i){
        byte[] temp=new byte[4];
        temp[0]=RC(i);
        temp[1]=0x00;
        temp[2]=0x00;
        temp[3]=0x00;

        return ByteArrayToInt(temp);
    }
    /** 废弃 */
    public static byte RC(int i){
        if(i==1)
            return 0x01;
        else
            return xtime(RC(i-1));
    }
    /** 废弃 */
    public static byte xtime(byte input){
        int low=input&(0b00001111);
        int high=((input)&(0b11110000))>>>4;
        return Xtime.xtimeTable[high][low];
    }


    public static int ByteArrayToInt(byte[] input){
        int result=0;
        result=input[0]&0xff;
        result=result<<8 | input[1] & 0xff;
        result=result<<8 | input[2] & 0xff;
        result=result<<8 | input[3] & 0xff;
        return result;
    }

}
