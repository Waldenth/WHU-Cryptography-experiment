package encryptTools.rc4;

public class Init {
	public static void InitStable(byte[]password){
        Stable.LinearFill();
        byte[]Rtable=new byte[256];
        int passwordLength=password.length;
        int j=0;
        for(int i=0;i<256;i++){
            Rtable[i]=password[i%passwordLength];
        }
        for(int i=0;i<256;i++){
            j=(j+Stable.S[i]+Rtable[i]+256)%256;
            swap(Stable.S,i,j);
        }
    }    
    public static void swap(byte[]data,int i,int j){
        // for debug
        if(i>=data.length||j>=data.length){
            System.out.println("Error: out of index");
            System.exit(-1);
        }
        byte tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
}
