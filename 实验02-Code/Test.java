import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
    static byte[] plainTextForTest={
        0,0,1,1,0,0,0,0,
        0,0,1,1,0,0,0,1,
        0,0,1,1,0,0,1,0,
        0,0,1,1,0,0,1,1,
        0,0,1,1,0,1,0,0,
        0,0,1,1,0,1,0,1,
        0,0,1,1,0,1,1,0,
        0,0,1,1,0,1,1,1
    };
    static byte[] cipherTextForTest={
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
        //TestDecrypt();
        EncryptFiles("key.txt","data.txt");
    }
    //给定路径,加密文件,暂时只支持UTF-8    
    public static boolean EncryptFiles(String keyPath,String filePath) throws IOException{
        /*
        Path filepath = Paths.get(path);
        char[] keyarray=(Files.readString(filepath).replaceAll("(\r\n|\r|\n|\n\r|\\s*)", "") ).toCharArray();
        */
        char[]keyArray=Files.readString(Paths.get(keyPath)).replaceAll("(\r\n|\r|\n|\n\r|\\s*)", "").toCharArray();;
        if(keyArray.length!=64){
            System.out.println("Error: the key length is not right (need 64 bits)");
            return false;
        }
        byte[]key64=new byte[64];
        for(int i=0;i<64;i++){
            key64[i]=(byte)(keyArray[i]-'0');
        }
        String dataFlow=Files.readString(Paths.get(filePath));
        byte[] data=dataFlow.getBytes("UTF-8");

        //System.out.println("SourcedataBytes="+data.length);

        StringBuffer bitString=new StringBuffer();
        for(int i=0;i<data.length;i++){
            String tmp=Integer.toBinaryString(data[i]);
            if(tmp.length()<8){
                for(int j=0;j<8-tmp.length();j++)
                    tmp="0"+tmp;
            }else if(tmp.length()>8){
                tmp=tmp.substring(tmp.length()-8);
            }
            bitString.append(tmp);
        }

        byte bitdata[]=new byte[bitString.length()];
        for(int i=0;i<bitString.length();i++){
            bitdata[i]=(byte)(bitString.charAt(i)-'0');
        }

        //System.out.println("bitStr="+bitString.length()+" bitdata="+bitdata.length);

        //有多少个完整的64bit
        int fullyEncryptSegment=bitdata.length/64;

        //生成子密钥
        byte[][] subkeys=GenerateKey.GeneateSubkeys(key64);

        for(int i=0;i<fullyEncryptSegment;i++){
            byte[] tmpCipherText=ENCRYPT.encryptData(Arrays.copyOfRange(bitdata, i*64, i*64+64), key64);
            System.arraycopy(tmpCipherText,0,bitdata, i*64, 64);
        }
        /*
        System.out.println(bitdata.length);      
        for(int i=0;i<bitdata.length;i++){
            System.out.print(bitdata[i]);
            if((i+1)%8==0)
                System.out.print(" ");
        }

        System.out.println("");
        */
        byte[]cipherText=ConvertBitToByte(bitdata);

        //System.out.println("bit="+bitdata.length+" Text="+cipherText.length);

        /*
        for(int i=0;i<cipherText.length;i++){
            System.out.print(cipherText[i]);
            if((i+1)%8==0)
                System.out.print(" ");
        }

        String cipherStr=new String(cipherText,"UTF-8");
        System.out.println(cipherStr);
        */
        String cipherStr=new String(cipherText,"UTF-8");
        System.out.println("+--------------------------+");
        System.out.println("|    UTF-8编码的密文       |");
        System.out.println("+--------------------------+");
        System.out.println(cipherStr);
        /*
        BufferedWriter out = new BufferedWriter(new FileWriter("密文.txt"));
        System.out.println(cipherStr);
        out.write(cipherText);
        //out.write(cipherText[0]);
        out.close();
        System.out.println("加密文件创建成功！");
        */
        File file = new File("密文.txt");  //创建文件对象
        FileOutputStream output = new FileOutputStream(file);
        output.write(cipherText);
        output.close();
        System.out.println("+-----------------+");
        System.out.println("|  导出密文成功!  |");
        System.out.println("+-----------------+");
        return true;
    }

    static byte[] ConvertBitToByte(byte[] input){
        byte[] bts=new byte[input.length/8];
        StringBuffer tmp;
        int k=0;
        for(int i=0;i<input.length;i+=8){
            tmp=new StringBuffer();
            for(int j=0;j<8;j++){
                char c=(char)('0'+(int)input[i+j]);
                tmp.append(c);
            }
            //System.out.println(tmp.toString());
            bts[k]=(byte)Integer.parseInt(tmp.toString(),2);
            k++;
        }
        return bts;
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
