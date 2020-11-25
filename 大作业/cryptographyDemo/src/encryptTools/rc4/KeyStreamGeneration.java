package encryptTools.rc4;

public class KeyStreamGeneration {
	public static byte[] GenerationKey(int needlength){
        byte []key=new byte[needlength];
        int i=0;
        int j=0;
        for(int t=0;t<needlength;t++){
            i=(i+1)%256;
            j=(j+Stable.S[i]+256)%256;
            Init.swap(Stable.S, i, j);
            key[t]=(byte)((256+Stable.S[i]+Stable.S[j])%256);
        }
        return key;
    }
}
