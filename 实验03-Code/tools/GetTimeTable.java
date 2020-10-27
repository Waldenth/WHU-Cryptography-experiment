package tools;
import AES.Xtime;

public class GetTimeTable {
    public static void main(String[]args){
        byte input1=0x0E;//对应计算的xtime表
        byte input2=0b00000000;
        byte add =1;
        int count=0;
        byte ans;
        
        while(count<256){
            int i=0;
            System.out.printf("{");
            while(i<16){
                ans=GFtime(input1, input2);
                input2=(byte)(input2+add);
                i++;
                count++;
                System.out.printf("(byte)0x%x,",ans);
            }
            System.out.println("},");
        }  
    }
    private static byte GFtime(byte input1,byte input2){
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
                ans=(byte)(input2^Xtime.xtimeTable[high][low]);
                break;
            case 0x04:
                ans=GFtime((byte)0x02, GFtime((byte)0x02, input2));
                //(byte)(GFtime((byte)0x02, input2)^GFtime((byte)0x02, input2));
                break;
            case 0x08:
                ans=GFtime((byte)0x02,GFtime((byte)0x02, GFtime((byte)0x02, input2)));
                break;
            case 0x09:
                ans=(byte)(GFtime((byte)0x08, input2)^input2);
                //(byte)(GFtime((byte)0x02,GFtime((byte)0x02, GFtime((byte)0x02, input2)))^input2);
                break;
            case 0x0b:
                ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x03, input2));
                break;
            case 0x0d:
                ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x04, input2)^input2);
                break;
            case 0x0e:
                ans=(byte)(GFtime((byte)0x08, input2)^GFtime((byte)0x04, input2)^GFtime((byte)0x02, input2));
                break;
            default:
                System.out.println("Error: can not calcullate in GF(2^8)");
                System.exit(-1);
        }
        return ans;
    }
}
