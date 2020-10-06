import encrypt.*;
import encrypt.keygenerate.GenerateKey;
public class Test {
    public static void main(String[]args){
        byte[] key={
            0,0,1,1,0,0,0,1,
            0,0,1,1,0,0,1,0,
            0,0,1,1,0,0,1,1,
            0,0,1,1,0,1,0,0,
            0,0,1,1,0,1,0,1,
            0,0,1,1,0,1,1,0,
            0,0,1,1,0,1,1,1,
            0,0,1,1,1,0,0,0,
        };
        byte subkeys[][]=GenerateKey.GeneateSubkeys(key);
        for(int i=0;i<16;i++){
            for(int j=0;j<48;j++){
                System.out.print(subkeys[i][j]);
                if((j+1)%8==0)
                    System.out.print(" ");
            }
            System.out.println("");
        }
    }    
}
