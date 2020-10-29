import RC4.*;
import RC4.FileOperate.FileAPI;
public class testRC4 {
    public static void main(String[]args){
        /*byte password[]={1,1,0,1};
        Init.InitStable(password);
        byte[]key=new byte[100];
        key=KeyStreamGeneration.GenerationKey(100);
        for(int i=0;i<100;i++)
            System.out.print(key[i]+" ");
        */
        FileAPI.HandleFile(false,"key.txt" , true, "Histoie.jpg","CipherHistoie.jpg");
        FileAPI.HandleFile(false,"key.txt" , true, "CipherHistoie.jpg","DecryptedHistoie.jpg");
    }
}
