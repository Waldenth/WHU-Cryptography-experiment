import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import encrypt.keygenerate.*;
import encrypt.*;
//import encrypt.keygenerate.GenerateKey;
public class Test {
    public static void main(String[] args) throws IOException {
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
        System.out.println("+-----------------------+");
        if(args.length!=0){
            Path path = Paths.get(args[0]);
            char[] keyarray=(Files.readString(path).replaceAll("(\r\n|\r|\n|\n\r|\\s*)", "") ).toCharArray();
            System.out.println("Your key is:");
            System.out.print("\t");
            int i=0;
            for(char tmp:keyarray){
                key[i]=(byte)(tmp-'0');
                System.out.print(tmp);
                i++;
                if(i%8==0)
                    System.out.print(" ");
            }
            System.out.println("");
            System.out.println("Please confirmed");
        }
        
        byte subkeys[][]=GenerateKey.GeneateSubkeys(key);
        System.out.println("+-----------------------+");
        System.out.println("The subkeys are:");
        for(int i=0;i<16;i++){
            for(int j=0;j<48;j++){
                System.out.print(subkeys[i][j]);
                if((j+1)%8==0)
                    System.out.print(" ");
            }
            System.out.println("");
        }
        byte plainText[]={
            0,0,1,1,0,0,0,0,
            0,0,1,1,0,0,0,1,
            0,0,1,1,0,0,1,0,
            0,0,1,1,0,0,1,1,
            0,0,1,1,0,1,0,0,
            0,0,1,1,0,1,0,1,
            0,0,1,1,0,1,1,0,
            0,0,1,1,0,1,1,1
        };
        System.out.println("The cipherText is:");
        System.out.println("+-----------------------+");
        byte[] cipheText=ENCRYPT.encryptData(plainText, key);
        for(int i=0;i<cipheText.length;i++){
            System.out.print(cipheText[i]);
            if( (i+1)%8==0)
                System.out.print(" ");
        }
        System.out.println("");
        System.out.println("+-----------------------+");
    }    
}
