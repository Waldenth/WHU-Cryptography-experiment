package encryptTools.des.encrypt;

import encryptTools.des.encrypt.box.SBox;

public class S {
	public static byte[] SWork(byte[] input){
        byte[] middledata=new byte[32];
        for(int i=0,a=0;i<48;i+=6,a+=4){
            int row=input[i]*2+input[i+5];//行号b1b6
            int column=input[i+1]*8+input[i+2]*4+input[i+3]*2+input[i+4];//列号b2b3b4b5
            
            String tmp=Integer.toBinaryString(SBox.box[i/6][row][column]);
            if(tmp.length()<4){
                int j=tmp.length();
                for(;j<4;j++){
                    tmp="0".concat(tmp);
                }
            }
            char[] b=new char[4];
            for(int j=0;j<4;j++){
                b[j]=tmp.charAt(j);
            }
            /*
            for(int j=0;j<b.length;j++)
                System.out.print(b[j]);
            System.out.println("");
            */
            for(int n=0;n<4;n++){
                middledata[a+n]=(byte)(b[n]-'0');
            }
        }
        return middledata;
    }
}
