package tools;
import AES.Xtime;

public class GetTimeTable {
    public static void main(String[]args){
        byte input=0b00000000;
        byte add =1;
        int count=0;
        while(count<256){
            int i=0;
            byte ans;
            //System.out.printf(" input=0x%x\n",input);
            System.out.printf("{");
            while(i<16){
                int high=(input>>>4)&(0b00001111);
                int low=(input)&(0b00001111);
                ans=(byte)(input^Xtime.xtimeTable[high][low]);
                input=(byte)(input+add);
                i++;
                count++;
                System.out.printf("(byte)0x%x,",ans);
            }
            System.out.println("},");
            //System.out.printf(" input=0x%x\n",input);
        }
        
    }
}
