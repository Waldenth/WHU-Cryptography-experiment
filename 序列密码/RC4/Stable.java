package RC4;

public class Stable {
    static byte[]S=new byte[256];
    public static void LinearFill(){
        for(int i=0;i<256;i++)
            S[i]=(byte)i;
    }
}
