import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import encrypt.keygenerate.*;
import encrypt.*;
//import encrypt.keygenerate.GenerateKey;
public class Test {
    static byte[]keyForTest={
        0,0,1,1,0,0,0,1,
        0,0,1,1,0,0,1,0,
        0,0,1,1,0,0,1,1,
        0,0,1,1,0,1,0,0,
        0,0,1,1,0,1,0,1,
        0,0,1,1,0,1,1,0,
        0,0,1,1,0,1,1,1,
        0,0,1,1,1,0,0,0,
    };
    static byte plainTextForTest[]={
        0,0,1,1,0,0,0,0,
        0,0,1,1,0,0,0,1,
        0,0,1,1,0,0,1,0,
        0,0,1,1,0,0,1,1,
        0,0,1,1,0,1,0,0,
        0,0,1,1,0,1,0,1,
        0,0,1,1,0,1,1,0,
        0,0,1,1,0,1,1,1
    };
    static byte cipherTextForTest[]={
        1,0,0,0,1,0,1,1, 
        1,0,1,1,0,1,0,0, 
        0,1,1,1,1,0,1,0, 
        0,0,0,0,1,1,0,0,
        1,1,1,1,0,0,0,0, 
        1,0,1,0,1,0,0,1, 
        0,1,1,0,0,0,1,0, 
        0,1,1,0,1,1,0,1
    };

    public static void main(String[] args) throws IOException {
        
        /*
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
        }*/
        
        //byte subkeys[][]=GenerateKey.GeneateSubkeys(key);
        /*
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
        */

        /*
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
        */
        /*
        char C='我';
        byte[] byteArray=String.valueOf(C).getBytes("UTF-8");
        for(byte tmp:byteArray){
            System.out.println(tmp);
            System.out.println(Integer.toBinaryString(tmp));
        }
        String recover=new String(byteArray,"UTF-8");
        System.out.println(recover);
        */
        //TestEncrypt();
        TestDecrypt();
    }
    //给定路径,加密文件    
    public static boolean EncryptFiles(String keyPath,String filePath) throws IOException{
        /*
        Path filepath = Paths.get(path);
        char[] keyarray=(Files.readString(filepath).replaceAll("(\r\n|\r|\n|\n\r|\\s*)", "") ).toCharArray();
        */
        char[]keyArray=Files.readString(Paths.get(keyPath)).replaceAll("(\r\n|\r|\n|\n\r|\\s*)", "").toCharArray();;
        
        return false;
    }
    //测试加密
    public static void TestEncrypt(){
        System.out.println("+-------------------------+");
        System.out.println("|     默认加密测试        |");
        System.out.println("+-------------------------+--------------------------------------------------------------+");
        System.out.print("|  64位密钥: ");
        Print(keyForTest,false);
        System.out.print("|  64位明文: ");
        Print(plainTextForTest, true);
        byte[][]subkeys=GenerateKey.GeneateSubkeys(keyForTest);
        for(int i=0;i<subkeys.length;i++){
            System.out.printf("|  子密钥K%2d: ",i+1);
            if(i==subkeys.length-1)
                Print(subkeys[i], true);
            else
                Print(subkeys[i], false);
        }
        byte[]cipherText=ENCRYPT.encryptData(plainTextForTest, keyForTest,true);
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.print("|  64位密文: ");
        Print(cipherText, true);
    }
    //测试解密
    public static void TestDecrypt(){
        System.out.println("+-------------------------+");
        System.out.println("|     默认解密测试        |");
        System.out.println("+-------------------------+--------------------------------------------------------------+");
        System.out.print("|  64位密钥: ");
        Print(keyForTest,false);
        System.out.print("|  64位密文: ");
        Print(cipherTextForTest, true);
        byte[] plainText=DECRYPT.decryptData(cipherTextForTest, keyForTest);
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.print("|  64位明文: ");
        Print(plainText,true);
    }
    //格式化输出
    public static void Print(byte[]data,boolean isnewtable){
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]);
            if((i+1)%8==0)
                System.out.print(" ");
        }
        System.out.print("    |");
        if(isnewtable){
            if(data.length==64)
                System.out.println("\n"+"+----------------------------------------------------------------------------------------+");
            else if(data.length==48)
                System.out.println("\n"+"+-----------------------------------------------------------------------+");
        }
        else
            System.out.println("");
    }

}
